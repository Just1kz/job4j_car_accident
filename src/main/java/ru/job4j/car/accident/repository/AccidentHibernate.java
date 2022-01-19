package ru.job4j.car.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.model.AccidentType;
import ru.job4j.car.accident.model.Rule;
import java.util.List;


@Repository
public class AccidentHibernate implements AccidentRepository,
                                                                                   AccidentTypeRepository,
                                                                                   RuleRepository {
    private final SessionFactory sf;

    @Autowired
    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public List<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Accident> rsl = session.createQuery(
                    "select distinct c from Accident c join fetch c.rules order by c.id").getResultList();
            session.getTransaction().commit();
            return rsl;
        }
    }

    @Override
    public void addOrUpdateAccident(Accident accident, int type, String[] ids) {
        AccidentType accidentType = findTypeByID(type);
        accident.setAccidentType(accidentType);

        for (String rsl : ids) {
            accident.addRules(findRuleByID(Integer.parseInt(rsl)));
        }

            saveOrUpdate(accident);
    }

    public void saveOrUpdate(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(accident);
            session.getTransaction().commit();
        }
    }

    @Override
    public Accident findAccidentByID(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Accident accident = session.createQuery(
                            "select distinct c from Accident c join fetch c.rules where c.id = :id order by c.id",
                            Accident.class)
                    .setParameter("id", id)
                    .uniqueResult();
            session.getTransaction().commit();
            return accident;
        }
    }

    @Override
    public Accident findAccidentByNameAndAddress(String name, String address) {
        try (Session session = sf.openSession()) {
            return (Accident) session.createQuery("select distinct c from Accident c join fetch c.rules where c.name = :name and c.address = :address order by c.id")
                    .setParameter("name", name)
                    .setParameter("address", address)
                    .uniqueResult();
        }
    }

    @Override
    public List<AccidentType> getAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType").list();
        }
    }

    @Override
    public AccidentType findTypeByID(int id) {
        try (Session session = sf.openSession()) {
            return (AccidentType) session.createQuery(
                    "from AccidentType where id = :id order by id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public Rule findRuleByID(int id) {
        try (Session session = sf.openSession()) {
            return (Rule) session.createQuery(
                    "from Rule where id = :id order by id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public List<Rule> getAllRulesOfAccident(int id) {
        return null;
    }

    @Override
    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule order by id ").list();
        }
    }
}
