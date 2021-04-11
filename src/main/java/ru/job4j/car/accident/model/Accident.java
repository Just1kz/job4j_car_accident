package ru.job4j.car.accident.model;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
public class Accident {
    private int id;
    private String name;
    private AccidentType accidentType;
    private String text;
    private String address;
    private String status = "Зарегистрирована";
    private Set<Rule> rules;

    public Accident() {
    }

    public Accident(String name, String text, String address) {
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, String text, String address, String status) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.status = status;
    }

    public Accident(String name, AccidentType accidentType, String text, String address) {
        this.name = name;
        this.accidentType = accidentType;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, AccidentType accidentType, String text, String address, Set<Rule> rules) {
        this.name = name;
        this.accidentType = accidentType;
        this.text = text;
        this.address = address;
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public void addRules(Rule rule) {
        rules.add(rule);
    }

    public StringBuffer getRulesString() {
        StringBuffer rsl = new StringBuffer();
        for (Rule zxc : rules) {
            rsl.append(zxc.getName()).append(System.lineSeparator());
        }
        return rsl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
