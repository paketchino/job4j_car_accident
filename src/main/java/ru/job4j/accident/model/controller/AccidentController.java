package ru.job4j.accident.model.controller;

import lombok.AllArgsConstructor;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.service.AccidentServiceData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.accident.service.RuleServiceData;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentServiceData accidentService;
    private final AccidentTypeRepository accidentTypeRepository;
    private final AuthorityRepository authorityRepository;
    private final RuleServiceData ruleServiceData;

    @GetMapping("/createAccident")
    public String addAccident(Model model) {
        Set<Rule> rules = new HashSet<>();
        rules.add(new Rule(1, "Статья 1"));
        rules.add(new Rule(2, "Статья 2"));
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "createAccident";
    }

   @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident,
                               @RequestParam("file") MultipartFile file,
                               HttpServletRequest req) throws Exception {
        String[] ids = req.getParameterValues("rIds");
        accident.setPhoto(file.getBytes());
        accidentService.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String updateAccident() {
        return "formUpdateAccident";
    }

    @PostMapping("/changeAccident")
    public String changeAccident(@ModelAttribute Accident accident,
                                 @RequestParam("id") int id,
                                 @RequestParam("file") MultipartFile file,
                                 Model model,
                                 HttpServletRequest req)
            throws Exception {
        accident.setPhoto(file.getBytes());
        String[] ids = req.getParameterValues("rIds");
        model.addAttribute("accident", accidentService.findByIdAccident(id));
        return "redirect:/accident";
    }

    @GetMapping("/addRule")
    public String addRule() {
        return "addRule";
    }

    @PostMapping("/createRule")
    public String createRule(@ModelAttribute Rule rule) {
        ruleServiceData.save(rule);
        return "redirect:/index";
    }

    @GetMapping("/createAccidentType")
    public String createAccidentType() {
        return "createAccidentType";
    }

    @PostMapping("/saveAccidentType")
    public String saveAccidentType(@ModelAttribute AccidentType accidentType) {
        accidentTypeRepository.save(accidentType);
        return "redirect:/index";
    }

    @GetMapping("/createAuthority")
    public String createAuthority() {
        return "createAuthority";
    }

    @PostMapping("/saveAuthority")
    public String saveAuthority(@ModelAttribute Authority authority) {
        authorityRepository.save(authority);
        return "redirect:/index";
    }
}
