package com.safework.web.resource;

import com.safework.domain.model.Checkin;
import com.safework.domain.service.CheckinService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("/api/checkins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CheckinResource {

    @Inject
    CheckinService checkinService;

    @POST
    @Transactional
    public Response registrar(Checkin checkin) {
        Checkin salvo = checkinService.registrarCheckin(checkin);
        return Response.created(URI.create("/api/checkins/" + salvo.getId()))
                .entity(salvo)
                .build();
    }

    @GET
    public List<Checkin> listar(@QueryParam("teamId") Long timeId,
                                @QueryParam("from") String from,
                                @QueryParam("to") String to) {

        if (timeId == null || from == null || to == null) {
            throw new BadRequestException("Parâmetros teamId, from e to são obrigatórios");
        }

        LocalDate dataFrom = LocalDate.parse(from);
        LocalDate dataTo = LocalDate.parse(to);

        return checkinService.listarPorTimeEPeriodo(timeId, dataFrom, dataTo);
    }
}
