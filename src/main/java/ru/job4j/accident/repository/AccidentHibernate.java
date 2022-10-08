package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.util.DefaultMethod;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccidentHibernate {

    private final SessionFactory sessionFactory;

    public AccidentHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Accident save(Accident accident) {
        return (Accident) DefaultMethod.tx(session ->
                session.save(accident), sessionFactory);
    }

    public List<Accident> getAll() {
        return DefaultMethod.tx(session ->
                session.createQuery("from Accident").list(),
                sessionFactory);
    }

    public Optional<Accident> findById(int id) {
        return DefaultMethod.tx(session ->
                session.createQuery("from Accident where id=:Id")
                        .setParameter("Id", id)
                        .uniqueResultOptional(), sessionFactory);
    }

    public boolean update(Accident accident) {
        return DefaultMethod.tx(session ->
                session.createQuery("update Accident a set a.desc=:aDesc, "
                                + "a.address =:aAddress, a.numberCar=:aNumber,"
                                + "a.photo =:aPhoto where a.id =:aId")
                        .setParameter("aDesc", accident.getDesc())
                        .setParameter("aAddress", accident.getAddress())
                        .setParameter("aNumber", accident.getNumberCar())
                        .setParameter("aPhoto", accident.getPhoto())
                        .setParameter("aId", accident.getId())
                        .executeUpdate() > 0, sessionFactory);
    }

    public AccidentType saveAccType(AccidentType accidentType) {
        return (AccidentType) DefaultMethod.tx(session ->
                session.save(accidentType), sessionFactory);
    }

    public Rule saveRule(Rule rule) {
        return (Rule) DefaultMethod.tx(session ->
                session.save(rule), sessionFactory);
    }

    public List<AccidentType> findAllAccidentType() {
        return DefaultMethod.tx(session ->
                session.createQuery("from AccidentType").list(), sessionFactory);
    }

    public List<Rule> findAllRule() {
        return DefaultMethod.tx(session ->
                session.createQuery("from Rule").list(), sessionFactory);
    }

    public Optional<AccidentType> findByIdAccidentType(int id) {
        return DefaultMethod.tx(session ->
                session.createQuery("from AccidentType where id =:ATId")
                .setParameter("ATId", id).uniqueResultOptional(), sessionFactory);
    }

    public Optional<Rule> findByIdRule(int id) {
        return DefaultMethod.tx(session ->
                session.createQuery("from Rule where id = :RId")
                        .setParameter("RId", id).uniqueResultOptional(), sessionFactory);

    }
}
