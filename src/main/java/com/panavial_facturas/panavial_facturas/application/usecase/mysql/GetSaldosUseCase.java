package com.panavial_facturas.panavial_facturas.application.usecase.mysql;

import com.panavial_facturas.panavial_facturas.application.dto.mysql.SaldosResponse;
import com.panavial_facturas.panavial_facturas.domain.port.repository.mysql.SaldosRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetSaldosUseCase {
    private final SaldosRepositoryPort saldosRepositoryPort;
    public GetSaldosUseCase(SaldosRepositoryPort saldosRepositoryPort){
        this.saldosRepositoryPort= saldosRepositoryPort;
    }
    public List<SaldosResponse> execute(
            String ruc,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {
        if (ruc == null) {
            // ADMIN
            return saldosRepositoryPort.findByFechaRange(fechaInicio, fechaFin)
                    .stream()
                    .map(SaldosResponse::fromDomain)
                    .toList();
        }

        // USER
        return saldosRepositoryPort.findByRucAndFechaRange(
                        ruc,
                        fechaInicio,
                        fechaFin
                ).stream()
                .map(SaldosResponse::fromDomain)
                .toList();
    }


}
