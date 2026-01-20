package com.panavial_facturas.panavial_facturas.domain.port.repository.postgres;

import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;

import java.time.LocalDate;
import java.util.List;

public interface FacturaRepositoryPort {

    // USER
    List<Factura> findByRucAndFecha(
            String ruc,
            LocalDate fechaEmision
    );

    // ADMIN
    List<Factura> findByFecha(
            LocalDate fechaEmision
    );
}
