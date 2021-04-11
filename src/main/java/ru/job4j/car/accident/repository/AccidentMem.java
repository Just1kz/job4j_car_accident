package ru.job4j.car.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.model.AccidentType;
import ru.job4j.car.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidentData = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private Integer countForAccident = 0;
    private Integer countForAccidentType = 0;
    private Integer countForRule = 0;

    public static AccidentMem of() {
        AccidentMem accidentMem = new AccidentMem();
        accidentMem.addAccidentType(AccidentType.of(1, "Две машины"));
        accidentMem.addAccidentType(AccidentType.of(2, "Машина и человек"));
        accidentMem.addAccidentType(AccidentType.of(3, "Машина и велосипед"));
        accidentMem.addAccidentType(AccidentType.of(4, "Одна машина"));

        accidentMem.addRule(Rule.of(1, "Статья1"));
        accidentMem.addRule(Rule.of(2, "Статья2"));
        accidentMem.addRule(Rule.of(3, "Статья3"));
        accidentMem.addRule(Rule.of(4, "Статья4"));

        accidentMem.addAccident(new Accident("Проехал на красный",
                "водитель машины под номером таким то.....",
                "Красный проспект 45"), 4, new String[]{"3"});
        accidentMem.addAccident(new Accident("Пересёк двойную сплошную",
                "водитель машины под номером таким то.....", "Красный проспект 15"), 4,
                new String[]{"1", "3"});
        accidentMem.addAccident(new Accident("Разворот в неположенном месте",
                "водитель машины под номером таким то.....", "Красный проспект 35"), 4,
                new String[]{"2", "3"});
        accidentMem.addAccident(new Accident("Авария",
                "водитель машины под номером таким то.....", "Красный проспект 20"), 4,
                new String[]{"1"});
        accidentMem.addAccident(new Accident("Проехал на красный",
                "водитель машины под номером таким то.....",
                "Красный проспект 45"), 4, new String[]{"1", "2", "3"});

        return accidentMem;
    }

    public void addAccident(Accident accident, int type, String[] ids) {
        countForAccident++;
        AccidentType accidentType = findByIdAccidentType(type);
        Set<Rule> ruleSet = findByIdRuleInMassive(ids);
        accident.setId(countForAccident);
        accident.setAccidentType(accidentType);
        accident.setRules(ruleSet);
        accidentData.putIfAbsent(countForAccident, accident);
    }

    public void addAccidentType(AccidentType accidentType) {
        countForAccidentType++;
        accidentType.setId(countForAccidentType);
        accidentTypes.putIfAbsent(countForAccidentType, accidentType);
    }

    public void addRule(Rule rule) {
        countForRule++;
        rule.setId(countForRule);
        rules.putIfAbsent(countForRule, rule);
    }

    public void updateAccident(Accident accident, int type, String[] ids) {
        AccidentType accidentType = findByIdAccidentType(type);
        Set<Rule> ruleSet = findByIdRuleInMassive(ids);
        accident.setAccidentType(accidentType);
        accident.setRules(ruleSet);
        accidentData.put(accident.getId(), accident);
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
