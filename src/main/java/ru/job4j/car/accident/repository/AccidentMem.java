package ru.job4j.car.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.model.AccidentType;
import ru.job4j.car.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidentData = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private static final AtomicInteger ACC_ID_FOR_ACCIDENT = new AtomicInteger(0);
    private static final AtomicInteger ACC_ID_FOR_ACCIDENT_TYPE = new AtomicInteger(0);
    private static final AtomicInteger ACC_ID_FOR_RULE = new AtomicInteger(0);

    public AccidentMem() {
        addAccidentType(AccidentType.of(1, "Две машины"));
        addAccidentType(AccidentType.of(2, "Машина и человек"));
        addAccidentType(AccidentType.of(3, "Машина и велосипед"));
        addAccidentType(AccidentType.of(4, "Одна машина"));

        addRule(Rule.of(1, "Статья1"));
        addRule(Rule.of(2, "Статья2"));
        addRule(Rule.of(3, "Статья3"));
        addRule(Rule.of(4, "Статья4"));

        addOrUpdateAccident(new Accident("Проехал на красный",
                "водитель машины под номером таким то.....",
                "Красный проспект 45"), 4, new String[]{"3"});
        addOrUpdateAccident(new Accident("Пересёк двойную сплошную",
                "водитель машины под номером таким то.....", "Красный проспект 15"), 4,
                new String[]{"1", "3"});
        addOrUpdateAccident(new Accident("Разворот в неположенном месте",
                "водитель машины под номером таким то.....", "Красный проспект 35"), 4,
                new String[]{"2", "3"});
        addOrUpdateAccident(new Accident("Авария",
                "водитель машины под номером таким то.....", "Красный проспект 20"), 4,
                new String[]{"1"});
        addOrUpdateAccident(new Accident("Проехал на красный",
                "водитель машины под номером таким то.....",
                "Красный проспект 45"), 4, new String[]{"1", "2", "3"});

    }

    public void addOrUpdateAccident(Accident accident, int type, String[] ids) {
        AccidentType accidentType = findByIdAccidentType(type);
        Set<Rule> ruleSet = findByIdRuleInMassive(ids);
//        accident.setAccidentType(accidentType);
        accident.setRules(ruleSet);
        if (accident.getId() == 0) {
            accident.setId(ACC_ID_FOR_ACCIDENT.incrementAndGet());
            accidentData.put(accident.getId(), accident);
        } else  {
            accidentData.put(accident.getId(), accident);
        }
    }

    public void addAccidentType(AccidentType accidentType) {
        accidentType.setId(ACC_ID_FOR_ACCIDENT_TYPE.incrementAndGet());
        accidentTypes.putIfAbsent(accidentType.getId(), accidentType);
    }

    public void addRule(Rule rule) {
        rule.setId(ACC_ID_FOR_RULE.incrementAndGet());
        rules.putIfAbsent(rule.getId(), rule);
    }

    public Accident findByIdAccident(int id) {
        return accidentData.get(id);
    }

    public Set<Rule> findByIdRuleInMassive(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String zxc : ids) {
            rsl.add(rules.get(Integer.parseInt(zxc)));
        }
        return rsl;
    }

    public List<Accident> showAllAccident() {
        return new ArrayList<>(accidentData.values());
    }

    public AccidentType findByIdAccidentType(int id) {
        return accidentTypes.get(id);
    }

    public List<AccidentType> showAllAccidentType() {
        return new ArrayList<>(accidentTypes.values());
    }

    public List<Rule> showAllRule() {
        return new ArrayList<>(rules.values());
    }
}
