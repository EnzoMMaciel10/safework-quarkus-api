package com.safework.domain.service;

import com.safework.domain.model.Checkin;
import com.safework.domain.model.Usuario;
import com.safework.infra.repo.CheckinRepository;
import com.safework.infra.repo.UsuarioRepository;
import com.safework.web.exception.BusinessException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class CheckinService {

    @Inject
    CheckinRepository checkinRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public Checkin registrarCheckin(Checkin checkin) {
        if (checkin.getUsuario() == null || checkin.getUsuario().getId() == null) {
            throw new BusinessException("Usuário é obrigatório no check-in");
        }
        Usuario usuario = usuarioRepository.findById(checkin.getUsuario().getId());
        if (usuario == null) {
            throw new BusinessException("Usuário não encontrado");
        }

        LocalDate data = checkin.getData();
        if (data == null) {
            data = LocalDate.now();
            checkin.setData(data);
        }

        List<Checkin> existentes = checkinRepository.findByUsuarioAndData(usuario.getId(), data);
        if (!existentes.isEmpty()) {
            throw new BusinessException("Usuário já possui check-in nesse dia");
        }

        checkin.setUsuario(usuario);
        return checkinRepository.save(checkin);
    }

    public List<Checkin> listarPorTimeEPeriodo(Long timeId, LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new BusinessException("Período (from/to) é obrigatório");
        }
        return checkinRepository.findByTimeAndPeriodo(timeId, from, to);
    }
}
