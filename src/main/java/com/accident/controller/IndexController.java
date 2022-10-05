package com.accident.controller;

import com.accident.model.Accident;
import com.accident.service.AccidentServiceData;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Security;
import java.util.Optional;

@Controller
public class IndexController {

    private final AccidentServiceData accidentService;

    public IndexController(AccidentServiceData accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentService.getAll());
        return "/index";
    }

    @GetMapping("/photoAds/{accidentById}")
    public ResponseEntity<Resource> download(
            @PathVariable("accidentById") Integer accidentId) {
        Optional<Accident> accident = accidentService.findById(accidentId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(accident.get().getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(accident.get().getPhoto()));
    }
}
