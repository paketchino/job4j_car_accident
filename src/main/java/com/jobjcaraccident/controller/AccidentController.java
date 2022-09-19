package com.jobjcaraccident.controller;

import com.jobjcaraccident.model.Accident;
import com.jobjcaraccident.service.AccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AccidentController {
    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/createAccident")
    public String addAccident() {
        return "/createAccident";
    }

   @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident,
                               @RequestParam("file") MultipartFile file)
           throws Exception {
        accident.setPhoto(file.getBytes());
        accidentService.add(accident);
        return "redirect:/index";
    }
}
