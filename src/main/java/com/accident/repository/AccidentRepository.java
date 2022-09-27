package com.accident.repository;

import com.accident.model.Accident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
}
