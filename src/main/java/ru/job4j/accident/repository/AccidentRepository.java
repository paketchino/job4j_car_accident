package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
}
