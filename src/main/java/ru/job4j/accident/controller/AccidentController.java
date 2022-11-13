package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.service.AccidentServiceData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.accident.service.AccidentTypeServiceData;
import ru.job4j.accident.service.RuleServiceData;
import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentServiceData accidentService;
    private final AuthorityRepository authorityRepository;

    private final RuleServiceData ruleServiceData;

    private final AccidentTypeServiceData accidentTypeServiceData;

    @GetMapping("/createAccident")
    public String addAccident(Model model) {
        model.addAttribute("types", accidentTypeServiceData.getAll());
        model.addAttribute("rules", ruleServiceData);
        return "accident/createAccident";
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
    public String updateAccident(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findByIdAccident(id));
        return "accident/formUpdateAccident";
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
