package com.safework.web.resource;

import com.safework.domain.model.Time;
import com.safework.domain.service.TimeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimeResource {

    @Inject
    TimeService timeService;

    @POST
    public Response criar(Time time) {
        Time salvo = timeService.criar(time);
        return Response.status(Response.Status.CREATED)
                .entity(salvo)
                .build();
    }

    @GET
    public List<Time> listar() {
        return timeService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Time buscarPorId(@PathParam("id") Long id) {
        return timeService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public Time atualizar(@PathParam("id") Long id, Time time) {
        return timeService.atualizar(id, time);
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        timeService.remover(id);
        return Response.noContent().build();
    }
}
