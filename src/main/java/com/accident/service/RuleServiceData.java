package com.accident.service;

import com.accident.model.Rule;
import com.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RuleServiceData {

    private final RuleRepository ruleRepository;

    public RuleServiceData(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void create(Rule rule) {
        ruleRepository.save(rule);
    }

    public List<Rule> getAll() {
        var result = new ArrayList<Rule>();
        for (var rule : ruleRepository.findAll()) {
            result.add(rule);
        }
        return result;
    }

    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

}
