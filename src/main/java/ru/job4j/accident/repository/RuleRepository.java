package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.Optional;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    void create(Rule rule);

    Optional<Rule> findById(int id);

    boolean updateRule(Rule rule);
}
