package ru.job4j.car.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidentData = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();
    private Integer countForAccident = 0;
    private Integer countForAccidentType = 0;

    public static AccidentMem of() {
        AccidentMem accidentMem = new AccidentMem();
        accidentMem.addAccidentType(AccidentType.of(1, "Две машины"));
        accidentMem.addAccidentType(AccidentType.of(2, "Машина и человек"));
        accidentMem.addAccidentType(AccidentType.of(3, "Машина и велосипед"));
        accidentMem.addAccidentType(AccidentType.of(4, "Одна машина"));
        accidentMem.addAccident(new Accident("Проехал на красный",
                "водитель машины под номером таким то.....",
                "Красный проспект 45"), 4);
        accidentMem.addAccident(new Accident("Пересёк двойную сплошную",
                "водитель машины под номером таким то.....", "Красный проспект 15"), 4);
        accidentMem.addAccident(new Accident("Разворот в неположенном месте",
                "водитель машины под номером таким то.....", "Красный проспект 35"), 4);
        accidentMem.addAccident(new Accident("Авария",
                "водитель машины под номером таким то.....", "Красный проспект 20"), 4);
        return accidentMem;
    }

    public void addAccident(Accident accident, int type) {
        countForAccident++;
        AccidentType accidentType = findByIdAccidentType(type);
        accident.setId(countForAccident);
        accident.setAccidentType(accidentType);
        accidentData.putIfAbsent(countForAccident, accident);
    }

    public void addAccidentType(AccidentType accidentType) {
        countForAccidentType++;
        accidentType.setId(countForAccidentType);
        accidentTypes.putIfAbsent(countForAccidentType, accidentType);
    }

    public void updateAccident(Accident accident, int type) {
        AccidentType accidentType = findByIdAccidentType(type);
        accident.setAccidentType(accidentType);
        accidentData.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidentData.get(id);
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
}
