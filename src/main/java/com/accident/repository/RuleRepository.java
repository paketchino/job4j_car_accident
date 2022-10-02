package com.accident.repository;

import com.accident.model.Rule;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
