package com.accident.repository;

import com.accident.model.Accident;
import com.accident.util.DefaultMethod;
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
                                + "a.address=:aAddress, a.numberCar=:aNumber where a.id =:aId")
                        .setParameter(accident.getDesc(), "aDesc")
                        .setParameter(accident.getAddress(), "aAddress")
                        .setParameter(accident.getNumberCar(), "aNumber")
                        .setParameter(accident.getId(), "aId")
                        .executeUpdate() > 0, sessionFactory);
    }
}
