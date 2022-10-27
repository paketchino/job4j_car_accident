package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import org.springframework.stereotype.Service;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentServiceData {

    @Autowired
    private final AccidentRepository accidentRepository;

    @Autowired
    private final RuleRepository ruleRepository;

    public void create(Accident accident) {
        accidentRepository.save(accident);
    }

    public void create(Rule rule) {
        ruleRepository.create(rule);
    }

    public Optional<Rule> findByIdRule(int id) {
        return ruleRepository.findById(id);
    }

    public boolean update(Rule rule) {
        return ruleRepository.updateRule(rule);
    }

    public List<Accident> getAll() {
        var result = new ArrayList<Accident>();
        for (var accident : accidentRepository.findAll()) {
            result.add(accident);
        }
        return result;
    }

    public Optional<Accident> findByIdAccident(int id) {
        return accidentRepository.findById(id);
    }
}
