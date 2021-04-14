package ru.job4j.car.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.car.accident.model.Rule;

import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
