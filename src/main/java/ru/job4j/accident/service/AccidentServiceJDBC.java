package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentServiceJDBC {

    private final AccidentJdbcTemplate accidentsRepostiory;

    public Accident save(Accident accident) {
        return accidentsRepostiory.save(accident);
    }

    public List<Accident> findAll() {
        return accidentsRepostiory.getAll();
    }

    public List<Accident> findById(int id) {
        return accidentsRepostiory.findById(id);
    }

    public Accident update(Accident accident) {
        return accidentsRepostiory.update(accident);
    }
}
