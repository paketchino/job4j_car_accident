package ru.job4j.accident.model.controller;

import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.service.AuthorityServiceData;

@Controller
public class RegControl {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityServiceData authorityServiceData;

    public RegControl(PasswordEncoder passwordEncoder,
                      UserRepository userRepository,
                      AuthorityServiceData authorityServiceData) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityServiceData = authorityServiceData;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorityServiceData.findByAuthority("ROLE_USER"));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
