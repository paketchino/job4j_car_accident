package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorityServiceData {

    private final AuthorityRepository authorityRepository;

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

    public Optional<Authority> findById(int id) {
        return authorityRepository.findById(id);
    }
}
