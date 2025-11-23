package com.safework.infra.repo;

import com.safework.domain.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository {

    @Inject
    EntityManager em;

    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public List<Usuario> findByTimeId(Long timeId) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.time.id = :timeId", Usuario.class)
                .setParameter("timeId", timeId)
                .getResultList();
    }

    public Optional<Usuario> findByEmail(String email) {
        var list = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList();
        return list.stream().findFirst();
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            em.persist(usuario);
            return usuario;
        } else {
            return em.merge(usuario);
        }
    }

    public void delete(Usuario usuario) {
        em.remove(usuario);
    }
}
