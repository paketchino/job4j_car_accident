package ru.job4j.accident.repository;

import ru.job4j.accident.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
