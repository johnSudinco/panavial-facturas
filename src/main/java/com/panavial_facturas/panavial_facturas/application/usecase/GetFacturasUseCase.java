package com.panavial_facturas.panavial_facturas.application.usecase;

import com.panavial_facturas.panavial_facturas.application.dto.FacturaResponse;
import com.panavial_facturas.panavial_facturas.domain.port.repository.FacturaRepositoryPort;
import com.panavial_facturas.panavial_facturas.domain.port.service.UserIdentityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GetFacturasUseCase {

    private final FacturaRepositoryPort facturaRepository;

    public GetFacturasUseCase(FacturaRepositoryPort facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaResponse> execute(String ruc, LocalDate fecha) {

        return facturaRepository.findByRucAndFecha(ruc, fecha)
                .stream()
                .map(FacturaResponse::fromDomain)
                .toList();
    }
}


