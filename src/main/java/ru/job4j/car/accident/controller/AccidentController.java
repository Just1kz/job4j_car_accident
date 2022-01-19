package ru.job4j.car.accident.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.repository.AccidentHibernate;

@Controller
public class AccidentController {

    private final AccidentHibernate accidents;

    public AccidentController(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("allAccident", accidents.getAllAccidents());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.getAllAccidentType());
        model.addAttribute("rules", accidents.getAllRules());
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findAccidentByID(id));
        model.addAttribute("types", accidents.getAllAccidentType());
        model.addAttribute("rules", accidents.getAllRules());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident,
                       @RequestParam("name") String name,
                       @RequestParam("text") String text,
                       @RequestParam("address") String address,
                       @RequestParam("type.id") int id,
                       @RequestParam("rIds") String[] ids) {
        if (accident.getId() == 0) {
            accident = new Accident(name, text, address);
        }
        accidents.addOrUpdateAccident(accident, id, ids);
        return "redirect:/";
    }
}
