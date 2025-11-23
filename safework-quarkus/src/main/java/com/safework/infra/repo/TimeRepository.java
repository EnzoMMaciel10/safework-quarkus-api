package com.safework.infra.repo;

import com.safework.domain.model.Time;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TimeRepository {

    @Inject
    EntityManager em;

    // Salvar ou atualizar um time
    public void persist(Time time) {
        if (time.getId() == null) {
            em.persist(time);      // novo
        } else {
            em.merge(time);        // atualização
        }
    }

    // >>> ESTE É O MÉTODO QUE FALTAVA <<<
    public Time findById(Long id) {
        return em.find(Time.class, id);
    }

    public List<Time> listAll() {
        return em.createQuery("from Time", Time.class)
                .getResultList();
    }

    public Optional<Time> findByIdOptional(Long id) {
        return Optional.ofNullable(em.find(Time.class, id));
    }

    public void delete(Time time) {
        em.remove(time);
    }
}
