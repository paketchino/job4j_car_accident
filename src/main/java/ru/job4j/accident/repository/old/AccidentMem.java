package ru.job4j.accident.repository.old;

import ru.job4j.accident.model.Accident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccidentMem.class);

    private AtomicInteger atomicInteger;
    private HashMap<Integer, Accident> storage = new HashMap<>();

    public void add(Accident accident) {
        LOGGER.info("Добавление accident");
        accident.setId(atomicInteger.getAndIncrement());
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
        storage.replace(accident.getId(), accident);
    }
}
