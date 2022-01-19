package ru.job4j.car.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.car.accident.model.Accident;
import ru.job4j.car.accident.model.AccidentType;
import ru.job4j.car.accident.model.Rule;
import ru.job4j.car.accident.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexControl {
    private final AccidentRepository accidents;
    private final AccidentTypeRepository accidentType;
    private final RuleRepository rule;

    public IndexControl(AccidentRepository accidents, AccidentTypeRepository accidentType, RuleRepository rule) {
        this.accidents = accidents;
        this.accidentType = accidentType;
        this.rule = rule;
    }

    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("allAccident", accidents.getAllAccidents());
        List<Accident> res = new ArrayList<>();
        accidents.findAll().forEach(res::add);
        model.addAttribute("allAccident", res);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
//        model.addAttribute("types", accidents.getAllAccidentType());
//        model.addAttribute("rules", accidents.getAllRules());
        List<AccidentType> types = new ArrayList<>();
        accidentType.findAll().forEach(types::add);
        model.addAttribute("types", types);
        List<Rule> rules = new ArrayList<>();
        rule.findAll().forEach(rules::add);
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        List<AccidentType> types = new ArrayList<>();
        accidentType.findAll().forEach(types::add);
        List<Rule> rules = new ArrayList<>();
        rule.findAll().forEach(rules::add);
        Optional<Accident> accident = accidents.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int id,
                       @RequestParam("rIds") String[] ids) {
        Optional<AccidentType> accidentTypeX = accidentType.findById(id);
        accident.setAccidentType(accidentTypeX.get());
        for (String rsl: ids) {
            Optional<Rule> rules = rule.findById(Integer.parseInt(rsl));
            accident.addRules(rules.get());
        }
        accidents.save(accident);
        return "redirect:/";
    }
}
