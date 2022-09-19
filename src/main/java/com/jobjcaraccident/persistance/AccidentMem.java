package com.jobjcaraccident.persistance;

import com.jobjcaraccident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> storage = new HashMap<>();

    public void add(Accident accident) {
        storage.put(accident.getId(), accident);
    }

    /**
     * storage.values().stream().collect(Collectors.toList());
     */
    public List<Accident> findAll() {
        List<Accident> accidents = new ArrayList<>();
        for (Map.Entry<Integer, Accident> entry : storage.entrySet()) {
            accidents.add(entry.getValue());
        }
        return accidents;
    }

    /**
     * storage.entrySet().stream().filter(entry -> entry.getKey().equals(id)).map(Map.Entry::getValue).findFirst();
     */
    public Optional<Accident> findById(int id) {
        Optional<Accident> accidentOptional = Optional.empty();
        for (Map.Entry<Integer, Accident> entry : storage.entrySet()) {
            if (entry.getKey().equals(id)) {
                accidentOptional = Optional.of(entry.getValue());
            }
        }
        return accidentOptional;
    }
}
