package com.safework.domain.service;

import com.safework.domain.model.Time;
import com.safework.infra.repo.TimeRepository;
import com.safework.web.exception.BusinessException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TimeService {

    @Inject
    TimeRepository timeRepository;

    @Transactional
    public Time criar(Time time) {
        // garante que será criado um novo registro
        time.setId(null);
        timeRepository.persist(time);   // persist é void
        return time;                    // retorna o próprio objeto
    }

    public List<Time> listarTodos() {
        return timeRepository.listAll();
    }

    public Time buscarPorId(long id) {
        return timeRepository.findByIdOptional(id)
                .orElseThrow(() -> new BusinessException("Time não encontrado"));
    }

    @Transactional
    public Time atualizar(Long id, Time timeAtualizado) {
        Time existente = buscarPorId(id);
        existente.setNome(timeAtualizado.getNome());
        // se tiver mais campos em Time, atualiza aqui também
        return existente;
    }

    @Transactional
    public void remover(Long id) {
        Time existente = buscarPorId(id);
        timeRepository.delete(existente);
    }
}
