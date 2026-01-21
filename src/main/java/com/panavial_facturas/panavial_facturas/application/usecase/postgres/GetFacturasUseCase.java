package com.panavial_facturas.panavial_facturas.application.usecase.postgres;

import com.panavial_facturas.panavial_facturas.application.dto.postgres.FacturaResponse;
import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;
import com.panavial_facturas.panavial_facturas.domain.port.repository.postgres.FacturaRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class GetFacturasUseCase {

    private static final long MAX_DIAS_ADMIN = 31;
    private static final long MAX_DIAS_USER = 90;

    private final FacturaRepositoryPort facturaRepository;

    public GetFacturasUseCase(FacturaRepositoryPort facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaResponse> execute(
            String ruc,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {

        //  ADMIN
        if (ruc == null) {

            if (fechaInicio == null || fechaFin == null) {
                throw new IllegalArgumentException(
                        "ADMIN debe enviar fechaInicio y fechaFin"
                );
            }

            validarRango(fechaInicio, fechaFin, MAX_DIAS_ADMIN, "ADMIN");

            return facturaRepository
                    .findByFecha(fechaInicio, fechaFin)
                    .stream()
                    .map(FacturaResponse::fromDomain)
                    .toList();
        }

        //  USER
        if (fechaInicio == null && fechaFin == null) {
            throw new IllegalArgumentException(
                    "USER debe enviar al menos una fecha"
            );
        }

        if (fechaInicio != null && fechaFin != null) {
            validarRango(fechaInicio, fechaFin, MAX_DIAS_USER, "USER");
        }

        return facturaRepository
                .findByRucAndFecha(ruc, fechaInicio, fechaFin)
                .stream()
                .map(FacturaResponse::fromDomain)
                .toList();
    }

    private void validarRango(
            LocalDate inicio,
            LocalDate fin,
            long maxDias,
            String rol
    ) {
        if (fin.isBefore(inicio)) {
            throw new IllegalArgumentException(
                    "La fechaFin no puede ser menor que fechaInicio"
            );
        }

        long dias = ChronoUnit.DAYS.between(inicio, fin) + 1;

        if (dias > maxDias) {
            throw new IllegalArgumentException(
                    "El rango máximo permitido para " + rol +
                            " es de " + maxDias + " días"
            );
        }
    }
}
