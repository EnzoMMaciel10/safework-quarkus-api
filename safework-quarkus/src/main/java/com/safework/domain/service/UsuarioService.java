package com.safework.domain.service;

import com.safework.domain.model.Time;
import com.safework.domain.model.Usuario;
import com.safework.infra.repo.TimeRepository;
import com.safework.infra.repo.UsuarioRepository;
import com.safework.web.exception.BusinessException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TimeRepository timeRepository;

    public List<Usuario> listar(Long timeId) {
        if (timeId != null) {
            return usuarioRepository.findByTimeId(timeId);
        }
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new BusinessException("Usuário não encontrado");
        }
        return usuario;
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new BusinessException("Nome do usuário é obrigatório");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new BusinessException("Email é obrigatório");
        }
        if (usuario.getTime() == null || usuario.getTime().getId() == null) {
            throw new BusinessException("Time é obrigatório");
        }

        Time time = timeRepository.findById(usuario.getTime().getId());
        if (time == null) {
            throw new BusinessException("Time informado não existe");
        }
        usuario.setTime(time);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void remover(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}
