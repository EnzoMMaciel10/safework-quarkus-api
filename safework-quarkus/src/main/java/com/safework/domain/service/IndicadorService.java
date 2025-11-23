package com.safework.domain.service;

import com.safework.domain.model.Checkin;
import com.safework.domain.model.IndicadorTimeDTO;
import com.safework.infra.repo.CheckinRepository;
import com.safework.infra.repo.TimeRepository;
import com.safework.web.exception.BusinessException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class IndicadorService {

    private static final int N_MINIMO = 5;

    @Inject
    CheckinRepository checkinRepository;

    @Inject
    TimeRepository timeRepository;

    public IndicadorTimeDTO calcularIndicadores(Long timeId, LocalDate from, LocalDate to) {
        if (timeRepository.findById(timeId) == null) {
            throw new BusinessException("Time não encontrado");
        }
        if (from == null || to == null) {
            throw new BusinessException("Período (from/to) é obrigatório");
        }

        List<Checkin> checkins = checkinRepository.findByTimeAndPeriodo(timeId, from, to);
        long total = checkins.size();

        if (total == 0) {
            return new IndicadorTimeDTO(timeId, 0.0, 0.0, 0.0, 0L);
        }

        if (total < N_MINIMO) {
            throw new BusinessException("Quantidade de check-ins insuficiente para exibição (privacidade)");
        }

        double somaHumor = 0;
        double somaSono = 0;
        double somaCarga = 0;

        for (Checkin c : checkins) {
            somaHumor += c.getHumor();
            somaSono += c.getSono();
            somaCarga += c.getCarga();
        }

        return new IndicadorTimeDTO(
                timeId,
                somaHumor / total,
                somaSono / total,
                somaCarga / total,
                total
        );
    }
}
