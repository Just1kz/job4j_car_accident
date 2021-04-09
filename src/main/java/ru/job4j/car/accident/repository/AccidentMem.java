package ru.job4j.car.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.car.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidentData = new HashMap<>();
    private Integer count = 0;

    public Map<Integer, Accident> getAccidentData() {
        return accidentData;
    }

    public void addAccident(Accident accident) {
        count++;
        accident.setId(count);
        accidentData.putIfAbsent(count, accident);
    }

    public List<Accident> showAllAccident() {
        return new ArrayList<>(accidentData.values());
    }
}
