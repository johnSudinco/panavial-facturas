package com.panavial_facturas.panavial_facturas.domain.port.repository;

import com.panavial_facturas.panavial_facturas.domain.model.Factura;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FacturaRepositoryPort {

    List<Factura> findByRucAndFecha(
            String ruc,
            LocalDate fechaEmision
    );
}
