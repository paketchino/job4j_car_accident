package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import ru.job4j.accident.service.AuthorityServiceData;

@Controller
@AllArgsConstructor
public class RegControl {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityServiceData authorityServiceData;

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false)
                              String error, Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "User with this username is already registered!!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorityServiceData.findByAuthority("ROLE_USER"));
        userRepository.save(user);
        return "redirect:/login";
    }
}
