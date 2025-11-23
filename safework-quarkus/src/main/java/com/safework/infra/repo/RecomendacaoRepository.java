package com.safework.infra.repo;

import com.safework.domain.model.Recomendacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class RecomendacaoRepository {

    @Inject
    EntityManager em;

    public Recomendacao save(Recomendacao rec) {
        if (rec.getId() == null) {
            em.persist(rec);
            return rec;
        } else {
            return em.merge(rec);
        }
    }

    public Recomendacao findById(Long id) {
        return em.find(Recomendacao.class, id);
    }
}
