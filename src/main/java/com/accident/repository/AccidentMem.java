package com.accident.repository;

import com.accident.model.Accident;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccidentMem {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccidentMem.class);

    private HashMap<Integer, Accident> storage = new HashMap<>();

    private final SessionFactory sessionFactory;

    public AccidentMem(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Accident accident) {
        storage.put(accident.getId(), accident);
    }

    public List<Accident> findAll() {
        return storage.values().stream().collect(Collectors.toList());
    }

    public Optional<Accident> findById(int id) {
       return storage.entrySet()
               .stream()
               .filter(entry -> entry.getKey().equals(id))
               .map(Map.Entry::getValue).findFirst();
    }

    public void update(Accident accident) {
        for (Map.Entry<Integer, Accident> entry : storage.entrySet()) {
                 if (entry.getKey().equals(accident.getId())) {
                      storage.put(accident.getId(), accident);
                  }
              }
    }
}
