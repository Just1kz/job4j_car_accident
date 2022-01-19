package ru.job4j.car.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.car.accident.model.AccidentType;

import java.util.List;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
