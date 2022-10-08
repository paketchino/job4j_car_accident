package ru.job4j.accident.repository;

import ru.job4j.accident.model.AccidentType;
import org.springframework.data.repository.CrudRepository;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
