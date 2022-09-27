package com.accident.controller;

import com.accident.model.Accident;
import com.accident.model.Rule;
import com.accident.model.AccidentType;
import com.accident.service.AccidentServiceData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AccidentController {
    private final AccidentServiceData accidentService;

    public AccidentController(AccidentServiceData accidentService) {
        this.accidentService = accidentService;
    }

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
        return "/createAccident";
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
        return "/formUpdateAccident";
    }

    @PostMapping("/changeAccident")
    public String changeAccident(@ModelAttribute Accident accident,
                                 @RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        return "redirect:/accident";
    }
}
