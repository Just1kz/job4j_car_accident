package ru.job4j.car.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.car.accident.repository.AccidentJdbcTemplate;

@Controller
public class IndexControl {
//    private final AccidentJdbcTemplate accidents;
//
//    public IndexControl(AccidentJdbcTemplate accidents) {
//        this.accidents = accidents;
//    }
//
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("allAccident", accidents.getAllAccidents());
//        return "index";
//    }
}
