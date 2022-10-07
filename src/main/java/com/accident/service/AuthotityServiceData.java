package com.accident.service;

import com.accident.model.Authority;
import com.accident.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthotityServiceData {

    private final AuthorityRepository authorityRepository;

    public AuthotityServiceData(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void create(Authority authority) {
        authorityRepository.save(authority);
    }

    public List<Authority> getAll() {
        return authorityRepository.getAll();
    }

    public Optional<Authority> findById(int id) {
        return authorityRepository.findById(id);
    }

}
