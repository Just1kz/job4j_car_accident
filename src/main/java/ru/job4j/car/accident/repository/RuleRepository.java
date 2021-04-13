package ru.job4j.car.accident.repository;

import ru.job4j.car.accident.model.Rule;

import java.util.List;

public interface RuleRepository {

    public Rule findRuleByID(int id);

    public List<Rule> getAllRulesOfAccident(int id);

    public List<Rule> getAllRules();
}
