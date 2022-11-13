package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleServiceData {

    private final RuleRepository ruleRepository;

    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    public void save(Rule rule) {
        ruleRepository.save(rule);
    }

    public boolean findByIdForCheck(int id) {
        return ruleRepository.existsById(id);
    }

    public void update(Rule rule) {
        ruleRepository.save(rule);
    }

    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }
}
