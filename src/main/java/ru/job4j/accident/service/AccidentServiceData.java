package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import org.springframework.stereotype.Service;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentServiceData {

    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;

    public AccidentServiceData(AccidentRepository accidentRepository,
                               RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
    }

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
