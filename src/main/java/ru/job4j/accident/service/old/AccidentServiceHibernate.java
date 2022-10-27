package ru.job4j.accident.service.old;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.old.AccidentHibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentServiceHibernate {

    private final AccidentHibernate accidentHibernate;

    public AccidentServiceHibernate(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public Accident save(Accident accident) {
        return accidentHibernate.save(accident);
    }

    public List<Accident> findAll() {
        return accidentHibernate.getAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentHibernate.findById(id);
    }

    public AccidentType saveAccType(AccidentType accidentType) {
        return accidentHibernate.saveAccType(accidentType);
    }

    public Rule saveRule(Rule rule) {
        return accidentHibernate.saveRule(rule);
    }

    public boolean update(Accident accident) {
        return accidentHibernate.update(accident);
    }

    public List<Rule> findAllRules() {
        return accidentHibernate.findAllRule();
    }

    public List<AccidentType> findAllAccidentTypes() {
        return accidentHibernate.findAllAccidentType();
    }

    public Optional<AccidentType> findByIdAccidentType(int id) {
        return accidentHibernate.findByIdAccidentType(id);
    }

    public Optional<Rule> findByIdRule(int id) {
        return accidentHibernate.findByIdRule(id);
    }
}
