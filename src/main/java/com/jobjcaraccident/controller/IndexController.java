package com.jobjcaraccident.controller;

import com.jobjcaraccident.model.Accident;
import com.jobjcaraccident.service.AccidentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class IndexController {

    private final AccidentService accidentService;

    public IndexController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "/index";
    }

    @GetMapping("/photoAds/{accidentById}")
    public ResponseEntity<Resource> download(
            @PathVariable("accidentById") Integer accidentId) {
        Optional<Accident> accident = accidentService.findByID(accidentId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(accident.get().getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(accident.get().getPhoto()));
    }
}
