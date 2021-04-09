package ru.job4j.car.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        List<String> rsl = List.of("1", "2", "3", "4", "5");
        model.addAttribute("list", rsl);
        return "index";
    }
}
