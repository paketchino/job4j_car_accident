package com.accident.repository;

import com.accident.model.AccidentType;
import org.springframework.data.repository.CrudRepository;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
