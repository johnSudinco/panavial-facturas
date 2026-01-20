package com.panavial_facturas.panavial_facturas.domain.port.repository;

import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;

import java.time.LocalDate;
import java.util.List;

public interface FacturaRepositoryPort {

    List<Factura> findByRucAndFecha(
            String ruc,
            LocalDate fechaEmision
    );
}
