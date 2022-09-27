package com.accident.service;

import com.accident.model.Accident;
import com.accident.repository.AccidentHibernate;
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

    public boolean update(Accident accident) {
        return accidentHibernate.update(accident);
    }
}
