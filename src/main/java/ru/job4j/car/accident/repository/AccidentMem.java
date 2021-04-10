package ru.job4j.car.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.car.accident.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidentData = new ConcurrentHashMap<>();
    private Integer count = 0;

    public static AccidentMem of() {
        AccidentMem accidentMem = new AccidentMem();
        accidentMem.addAccident(new Accident("Проехал на красный",
                "водитель машины под номером таким то.....", "Красный проспект 45"));
        accidentMem.addAccident(new Accident("Пересёк двойную сплошную",
                "водитель машины под номером таким то.....", "Красный проспект 15"));
        accidentMem.addAccident(new Accident("Разворот в неположенном месте",
                "водитель машины под номером таким то.....", "Красный проспект 35"));
        return accidentMem;
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
