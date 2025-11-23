package com.safework.infra.repo;

import com.safework.domain.model.Checkin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class CheckinRepository {

    @Inject
    EntityManager em;

    public Checkin findById(Long id) {
        return em.find(Checkin.class, id);
    }

    public Checkin save(Checkin checkin) {
        if (checkin.getId() == null) {
            em.persist(checkin);
            return checkin;
        } else {
            return em.merge(checkin);
        }
    }

    public List<Checkin> findByUsuarioAndData(Long usuarioId, LocalDate data) {
        return em.createQuery(
                        "SELECT c FROM Checkin c WHERE c.usuario.id = :usuarioId AND c.data = :data",
                        Checkin.class)
                .setParameter("usuarioId", usuarioId)
                .setParameter("data", data)
                .getResultList();
    }

    public List<Checkin> findByTimeAndPeriodo(Long timeId, LocalDate from, LocalDate to) {
        return em.createQuery(
                        "SELECT c FROM Checkin c WHERE c.usuario.time.id = :timeId AND c.data BETWEEN :from AND :to",
                        Checkin.class)
                .setParameter("timeId", timeId)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }
}
