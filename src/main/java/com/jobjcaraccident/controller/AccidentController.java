package com.jobjcaraccident.controller;

import com.jobjcaraccident.model.Accident;
import com.jobjcaraccident.service.AccidentService;

public class AccidentController {

    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    /*
    Get
     */
    public String addAccident() {
        return "/addAccident";
    }

    /*
    Post
     */
    public String createAccident(Accident accident) {
        return "redirect:/index";
    }
}
