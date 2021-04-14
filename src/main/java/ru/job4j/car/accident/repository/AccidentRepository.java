package ru.job4j.car.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.car.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
}
