package ru.job4j.car.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.model.AccidentType;
import ru.job4j.car.accident.model.Rule;
import java.util.HashSet;
import java.util.List;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Rule> ruleRowMapper = ((rs, row) -> {
        Rule rule = Rule.of(
                rs.getInt("id"),
                rs.getString("name"));
        return rule;
    });

    private final RowMapper<Rule> ruleRowMapperManyToMany = ((rs, row) -> {
        Rule rule = findRuleByID(rs.getInt("id_rule"));
        return rule;
    });

    private final RowMapper<AccidentType> accidentTypeRowMapper = ((rs, row) -> {
        AccidentType type = AccidentType.of(
                rs.getInt("id"),
                rs.getString("name"));
        return type;
    });

    private final RowMapper<Accident> accidentRowMapper = ((rs, row) -> {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setAccidentType(findTypeByID(rs.getInt("type_id")));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setStatus(rs.getString("status"));
        accident.setRules(new HashSet<>(getAllRulesOfAccident(rs.getInt("id"))));
        return accident;
    });

    public List<Accident> getAllAccidents() {
        return jdbc.query(
                "select id, name, type_id, text, address, status  from accident order by id",
                accidentRowMapper);
    }

    public List<AccidentType> getAllAccidentType() {
        return jdbc.query(
                "select id, name from type order by id",
                accidentTypeRowMapper);
    }

    public List<Rule> getAllRules() {
        return jdbc.query(
                "select id, name from rule order by id",
                ruleRowMapper);
    }

    public List<Rule> getAllRulesOfAccident(int id) {
        return jdbc.query(
                "select id_accident, id_rule from accident_rule where id_accident = ?",
                ruleRowMapperManyToMany, id);
    }

    public void addOrUpdateAccident(Accident accident, int type, String[] ids) {
        AccidentType accidentType = findTypeByID(type);
        accident.setAccidentType(accidentType);

        int id = 0;

        if (accident.getId() == 0) {
            jdbc.update("insert into accident (name, type_id, text, address, status) values (?, ?, ?, ?, ?)",
                    accident.getName(),
                    accident.getAccidentType().getId(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getStatus());
            Accident accidentRsl = findAccidentByNameAndAddress(accident.getName(), accident.getAddress());
            id = accidentRsl.getId();
        } else {
            jdbc.update("update accident set name = ?, type_id = ?, text = ?, address = ?, status = ? where id = ?",
                    accident.getName(),
                    accident.getAccidentType().getId(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getStatus(),
                    accident.getId());
            jdbc.update("delete from accident_rule where id_accident = ? ",
                    accident.getId());
            id = accident.getId();
        }

        for (String rsl : ids) {
            jdbc.update("insert into accident_rule (id_accident, id_rule) VALUES (?, ?) ",
                    id,
                    Integer.parseInt(rsl));
        }
    }

    public Accident findAccidentByID(int id) {
        return jdbc.queryForObject(
                "select id, name, type_id, text, address, status from accident where id = ?",
                accidentRowMapper,
                id);
    }

    public Accident findAccidentByNameAndAddress(String name, String address) {
        return jdbc.queryForObject(
                "select id, name, type_id, text, address, status from accident where name = ? and address = ?",
                accidentRowMapper,
                name,
                address);
    }

    public AccidentType findTypeByID(int id) {
        return jdbc.queryForObject(
                "select id, name from type where id = ?",
                accidentTypeRowMapper,
                id);
    }

    public Rule findRuleByID(int id) {
        return jdbc.queryForObject(
                "select id, name from rule where id = ?",
                ruleRowMapper,
                id);
    }
}
