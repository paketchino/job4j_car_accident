package com.accident.service;

import com.accident.model.Accident;
import com.accident.repository.AccidentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentServiceData {

    private final AccidentRepository accidentRepository;

    public AccidentServiceData(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public void create(Accident accident) {
        accidentRepository.save(accident);
    }

    public List<Accident> getAll() {
        var result = new ArrayList<Accident>();
        for (var accident : accidentRepository.findAll()) {
            result.add(accident);
        }
        return result;
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }
}
