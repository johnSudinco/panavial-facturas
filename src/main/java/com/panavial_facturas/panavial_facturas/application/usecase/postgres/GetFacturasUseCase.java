package com.panavial_facturas.panavial_facturas.application.usecase.postgres;

import com.panavial_facturas.panavial_facturas.application.dto.postgres.FacturaResponse;
import com.panavial_facturas.panavial_facturas.domain.port.repository.postgres.FacturaRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetFacturasUseCase {

    private final FacturaRepositoryPort facturaRepository;

    public GetFacturasUseCase(FacturaRepositoryPort facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaResponse> execute(
            String ruc,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {

        // ADMIN
        if (ruc == null) {

            if (fechaInicio == null || fechaFin == null) {
                throw new IllegalArgumentException(
                        "ADMIN debe enviar fechaInicio y fechaFin"
                );
            }

            validarOrdenFechas(fechaInicio, fechaFin);

            return facturaRepository
                    .findByFecha(fechaInicio, fechaFin)
                    .stream()
                    .map(FacturaResponse::fromDomain)
                    .toList();
        }

        // USER
        if (fechaInicio == null && fechaFin == null) {
            throw new IllegalArgumentException(
                    "USER debe enviar al menos una fecha"
            );
        }

        if (fechaInicio != null && fechaFin != null) {
            validarOrdenFechas(fechaInicio, fechaFin);
        }

        return facturaRepository
                .findByRucAndFecha(ruc, fechaInicio, fechaFin)
                .stream()
                .map(FacturaResponse::fromDomain)
                .toList();
    }

    private void validarOrdenFechas(LocalDate inicio, LocalDate fin) {
        if (fin.isBefore(inicio)) {
            throw new IllegalArgumentException(
                    "La fechaFin no puede ser menor que fechaInicio"
            );
        }
    }
}
