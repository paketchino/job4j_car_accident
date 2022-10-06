package com.accident.repository;

import com.accident.model.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);

    void create(Authority authority);

    List<Authority> getAll();

    Optional<Authority> findById(int id);
}
