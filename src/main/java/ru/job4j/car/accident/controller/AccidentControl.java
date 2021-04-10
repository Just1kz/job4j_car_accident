package ru.job4j.car.accident.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit() {
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        if (accident.getId() == 0) {
            accidents.addAccident(accident);
        } else {
            accidents.updateAccident(accident);
        }
        return "redirect:/";
    }
}
