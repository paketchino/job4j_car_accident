package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccidentMem {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccidentMem.class);

    private HashMap<Integer, Accident> storage = new HashMap<>();

    private final SessionFactory sessionFactory;

    public AccidentMem(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Accident accident) {
        LOGGER.info("Добавление accident");
        storage.put(accident.getId(), accident);
    }

    public List<Accident> findAll() {
        LOGGER.info("Поиск всех accident");
        return new ArrayList<>(storage.values());
    }

    public Accident findById(int id) {
        LOGGER.info("Поиск по id");
        return storage.get(id);
    }

    public void update(Accident accident) {
        LOGGER.info("Обновление accident");
        for (Map.Entry<Integer, Accident> entry : storage.entrySet()) {
                 if (entry.getKey().equals(accident.getId())) {
                      storage.put(accident.getId(), accident);
                  }
              }
    }
}
