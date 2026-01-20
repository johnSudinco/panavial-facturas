package com.panavial_facturas.panavial_facturas.application.usecase.postgres;

import com.panavial_facturas.panavial_facturas.application.dto.postgres.FacturaResponse;
import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;
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

    public List<FacturaResponse> execute(String ruc, LocalDate fecha) {

        List<Factura> facturas;


        if (ruc == null) {
            // ADMIN → todas las facturas por fecha
            facturas = facturaRepository.findByFecha(fecha);
        } else {
            // USER → solo sus facturas
            facturas = facturaRepository.findByRucAndFecha(ruc, fecha);
        }

        return facturas.stream()
                .map(FacturaResponse::fromDomain)
                .toList();
    }
}

