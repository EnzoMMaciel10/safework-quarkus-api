package com.safework.web.resource;

import com.safework.domain.model.Usuario;
import com.safework.domain.service.UsuarioService;
import com.safework.web.exception.BusinessException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioService usuarioService;

    public static class LoginRequest {
        public String email;
        public String senha;
    }

    @POST
    @Path("/login")
    public Map<String, Object> login(LoginRequest request) {
        if (request.email == null || request.senha == null) {
            throw new BusinessException("Email e senha são obrigatórios");
        }

        Usuario usuario = usuarioService
                .listar(null)
                .stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(request.email)
                        && u.getSenha().equals(request.senha))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Credenciais inválidas"));

        String tokenFake = "fake-token-" + usuario.getId();

        return Map.of(
                "token", tokenFake,
                "usuarioId", usuario.getId(),
                "perfil", usuario.getPerfil().name()
        );
    }
}
