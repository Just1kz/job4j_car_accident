package ru.job4j.car.accident.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem accidents = AccidentMem.of();

//    public AccidentControl(AccidentMem accidents) {
//        this.accidents = accidents;
//    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("allAccident", accidents.showAllAccident());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.showAllAccidentType());
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", accidents.showAllAccidentType());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        if (accident.getId() == 0) {
            accidents.addAccident(accident, id);
        } else {
            accidents.updateAccident(accident, id);
        }
        return "redirect:/";
    }
}
