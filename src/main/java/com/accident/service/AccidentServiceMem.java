package com.accident.service;

import com.accident.model.Accident;
import com.accident.repository.AccidentMem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentServiceMem {
    private final AccidentMem accidentMem;

    public AccidentServiceMem(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Optional<Accident> findByID(int id) {
        return accidentMem.findById(id);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }
}
