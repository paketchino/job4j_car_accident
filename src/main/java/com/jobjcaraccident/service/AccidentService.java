package com.jobjcaraccident.service;

import com.jobjcaraccident.model.Accident;
import com.jobjcaraccident.persistance.AccidentMem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
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
}
