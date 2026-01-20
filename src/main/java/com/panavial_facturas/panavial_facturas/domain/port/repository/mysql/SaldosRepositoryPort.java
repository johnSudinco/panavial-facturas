package com.panavial_facturas.panavial_facturas.domain.port.repository.mysql;

import com.panavial_facturas.panavial_facturas.domain.model.mysql.Saldos;

import java.time.LocalDate;
import java.util.List;

public interface SaldosRepositoryPort {
    List<Saldos> findByRucAndFechaRange(
            String ruc,
            LocalDate fechaEmisionInicio,
            LocalDate fechaEmisionFin
    );
}
