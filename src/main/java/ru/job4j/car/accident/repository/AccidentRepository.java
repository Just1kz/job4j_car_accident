package ru.job4j.car.accident.repository;

import ru.job4j.car.accident.model.Accident;

import java.util.List;

public interface AccidentRepository {

    public List<Accident> getAllAccidents();

    public void addOrUpdateAccident(Accident accident, int type, String[] ids);

    public Accident findAccidentByID(int id);

    public Accident findAccidentByNameAndAddress(String name, String address);
}
