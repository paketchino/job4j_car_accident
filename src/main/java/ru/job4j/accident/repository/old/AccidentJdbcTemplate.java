package ru.job4j.accident.repository.old;

import ru.job4j.accident.model.Accident;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident save(Accident accident) {
        jdbc.update("insert into accidents (name, "
                        + "rule_id, "
                        + "accidentType_id, " + "address, "
                        + "numberCar, " + "desc, " + "photo, "
                        + "status) "
                        + "values (?, ?, ?, ?, ?, ?, ?)",
                accident.getName());
        accident.getRule();
        accident.getAccidentType();
        accident.getAddress();
        accident.getNumberCar();
        accident.getDescription();
        accident.getPhoto();
        accident.isStatus();
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name from accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    public List<Accident> findById(int id) {
        return jdbc.query("select id, name from accidents where id = ?",
                (resultSet, rowNum) -> {
            Accident accident = new Accident();
            accident.setId(resultSet.getInt("id"));
            accident.setName(resultSet.getString("name"));
            return accident;
        });
    }

    public Accident update(Accident accident) {
        jdbc.update("update accidents set name = ? where id = ?", accident.getId());
        return accident;
    }
}
