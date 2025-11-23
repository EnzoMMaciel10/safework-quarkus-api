package com.safework.web.resource;

import com.safework.domain.model.IndicadorTimeDTO;
import com.safework.domain.service.IndicadorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;

@Path("/api/indicadores")
@Produces(MediaType.APPLICATION_JSON)
public class IndicadorResource {

    @Inject
    IndicadorService indicadorService;

    @GET
    @Path("/teams/{timeId}")
    public IndicadorTimeDTO indicadoresPorTime(@PathParam("timeId") Long timeId,
                                               @QueryParam("from") String from,
                                               @QueryParam("to") String to) {
        LocalDate dataFrom = LocalDate.parse(from);
        LocalDate dataTo = LocalDate.parse(to);
        return indicadorService.calcularIndicadores(timeId, dataFrom, dataTo);
    }
}
