package com.safework.infra.repo;

import com.safework.domain.model.AcaoAplicada;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class AcaoAplicadaRepository {

    @Inject
    EntityManager em;

    public AcaoAplicada save(AcaoAplicada acao) {
        if (acao.getId() == null) {
            em.persist(acao);
            return acao;
        } else {
            return em.merge(acao);
        }
    }
}
