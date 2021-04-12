package ru.job4j.car.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.car.accident.repository.AccidentJdbcTemplate;
import ru.job4j.car.accident.repository.AccidentMem;

@Controller
public class IndexControl {
    private final AccidentMem accidents;

    public IndexControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("allAccident", accidents.showAllAccident());
        return "index";
    }
}
