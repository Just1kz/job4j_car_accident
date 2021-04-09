package ru.job4j.car.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.repository.AccidentMem;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        AccidentMem rsl = new AccidentMem();
        rsl.addAccident(new Accident("Проехал на красный",
                "водитель машины под номером таким то.....", "Красный проспект 45"));
        rsl.addAccident(new Accident("Пересёк двойную сплошную",
                "водитель машины под номером таким то.....", "Красный проспект 15"));
        rsl.addAccident(new Accident("Разворот в неположенном месте",
                "водитель машины под номером таким то.....", "Красный проспект 35"));
        model.addAttribute("allAccident", rsl.showAllAccident());
        return "index";
    }
}
