package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentTypeServiceData;

@Controller
@AllArgsConstructor
@RequestMapping("/accidentsTypes")
public class AccidentTypeController {

    private final AccidentTypeServiceData accidentTypeServiceData;

    @GetMapping("/createAccidentType")
    public String getAddAccidentType() {
        return "accidentType/createAccidentType";
    }

    @PostMapping("/saveAccidentType")
    public String save(AccidentType accidentType) {
        accidentTypeServiceData.create(accidentType);
        return "redirect:/index";
    }

}
