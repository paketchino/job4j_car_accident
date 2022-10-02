package com.accident.service;

import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.repository.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccidentTypeServiceData {

    private final AccidentTypeRepository accidentTypeRepository;

    public AccidentTypeServiceData(AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    public void create(AccidentType accidentType) {
        accidentTypeRepository.save(accidentType);
    }

    public List<AccidentType> getAll() {
        var result = new ArrayList<AccidentType>();
        for (var accidentType : accidentTypeRepository.findAll()) {
            result.add(accidentType);
        }
        return result;
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
