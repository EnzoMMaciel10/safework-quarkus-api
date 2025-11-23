package com.safework.web.resource;

import com.safework.domain.model.Usuario;
import com.safework.domain.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    public List<Usuario> listar(@QueryParam("teamId") Long timeId) {
        return usuarioService.listar(timeId);
    }

    @GET
    @Path("/{id}")
    public Usuario buscarPorId(@PathParam("id") Long id) {
        return usuarioService.buscarPorId(id);
    }

    @POST
    @Transactional
    public Response criar(Usuario usuario) {
        Usuario salvo = usuarioService.salvar(usuario);
        return Response.created(URI.create("/api/users/" + salvo.getId()))
                .entity(salvo)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Usuario atualizar(@PathParam("id") Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioService.salvar(usuario);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remover(@PathParam("id") Long id) {
        usuarioService.remover(id);
        return Response.noContent().build();
    }
}
