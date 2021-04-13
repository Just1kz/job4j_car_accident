package ru.job4j.car.accident.repository;

import ru.job4j.car.accident.model.AccidentType;

import java.util.List;

public interface AccidentTypeRepository {

    public List<AccidentType> getAllAccidentType();

    public AccidentType findTypeByID(int id);
}
