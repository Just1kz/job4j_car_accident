package ru.job4j.car.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private AccidentType accidentType;

    @Column(name = "text")
    private String text;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status = "Зарегистрирована";

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "accident_rule",
            joinColumns = @JoinColumn(name = "id_accident"),
            inverseJoinColumns = @JoinColumn(name = "id_rule") )
    private Set<Rule> rules = new HashSet<>();

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

    public static Accident of(String name, String text, String address,
                              AccidentType type, Set<Rule> rules) {
        Accident accident = new Accident();
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.accidentType = type;
        accident.rules = rules;
        return accident;
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
        this.rules.add(rule);
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
