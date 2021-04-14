package ru.job4j.car.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.model.AccidentType;
import ru.job4j.car.accident.model.Rule;

import javax.persistence.EntityManager;
import java.util.List;


//@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    @Autowired
    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct c from Accident c join fetch c.rules order by c.id").list();
        }
    }

    public void addOrUpdateAccident(Accident accident, int type, String[] ids) {
        AccidentType accidentType = findTypeByID(type);
        accident.setAccidentType(accidentType);

        for (String rsl : ids) {
            accident.getRules().add(findRuleByID(Integer.parseInt(rsl)));
//            accident.addRules(findRuleByID(Integer.parseInt(rsl)));
        }

            if (accident.getId() == 0) {
                addAccident(accident);
            } else {
//                updateAccident(accident);
                try (Session session = sf.openSession()) {
                    EntityManager em = session.getEntityManagerFactory().createEntityManager();
                    em.getTransaction().begin();
                    session.update(accident);
                    em.getTransaction().commit();
                }
            }
    }

    public void addAccident(Accident accident) {
        try (Session session = sf.openSession()) {
            EntityManager em = session.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            session.save(accident);
            em.getTransaction().commit();
        }
    }

    public void updateAccident(Accident accident) {
        try (Session session = sf.openSession()) {
            EntityManager em = session.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            session.createQuery(
                          "update Accident "
                            + "set name = :name, accidentType = :type, "
                            + "text = :text, address = :address, status = :status "
                            + "where id = :id")
                    .setParameter("name", accident.getName())
                    .setParameter("text", accident.getText())
                    .setParameter("address", accident.getAddress())
                    .setParameter("status", accident.getStatus())
                    .setParameter("type", accident.getAccidentType())
                    .setParameter("id", accident.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Accident findAccidentByID(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct c from Accident c join fetch c.rules where c.id = :id order by c.id",
                    Accident.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public Accident findAccidentByNameAndAddress(String name, String address) {
        try (Session session = sf.openSession()) {
            return (Accident) session.createQuery("select distinct c from Accident c join fetch c.rules where c.name = :name and c.address = :address order by c.id")
                    .setParameter("name", name)
                    .setParameter("address", address)
                    .uniqueResult();
        }
    }

    public List<AccidentType> getAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType").list();
        }
    }

    public AccidentType findTypeByID(int id) {
        try (Session session = sf.openSession()) {
            return (AccidentType) session.createQuery(
                    "from AccidentType where id = :id order by id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public Rule findRuleByID(int id) {
        try (Session session = sf.openSession()) {
            return (Rule) session.createQuery(
                    "from Rule where id = :id order by id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public List<Rule> getAllRulesOfAccident(int id) {
        return null;
    }

    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule order by id ").list();
        }
    }
}
