package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
