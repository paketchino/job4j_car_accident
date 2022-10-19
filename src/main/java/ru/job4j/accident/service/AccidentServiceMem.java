package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
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

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }
}
