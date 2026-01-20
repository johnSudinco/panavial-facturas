package com.panavial_facturas.panavial_facturas.domain.port.repository.mysql;

import com.panavial_facturas.panavial_facturas.domain.model.mysql.Saldos;

import java.time.LocalDate;
import java.util.List;

public interface SaldosRepositoryPort {

    // USER
    List<Saldos> findByRucAndFechaRange(
            String ruc,
            LocalDate fechaEmisionInicio,
            LocalDate fechaEmisionFin
    );

    // ADMIN
    List<Saldos> findByFechaRange(
            LocalDate fechaEmisionInicio,
            LocalDate fechaEmisionFin
    );
}
