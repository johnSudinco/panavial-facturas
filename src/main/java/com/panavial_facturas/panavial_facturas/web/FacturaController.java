package com.panavial_facturas.panavial_facturas.web;

import com.panavial_facturas.panavial_facturas.application.dto.FacturaResponse;
import com.panavial_facturas.panavial_facturas.application.usecase.GetFacturasUseCase;
import com.panavial_facturas.panavial_facturas.domain.model.Factura;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final GetFacturasUseCase useCase;

    public FacturaController(GetFacturasUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<FacturaResponse>> misFacturas(
            @RequestParam(required = false) LocalDate fecha,
            Authentication auth
    ) {
        String username = (auth != null) ? auth.getName() : "ANONYMOUS";

        return ResponseEntity.ok(
                useCase.execute(username, fecha)
        );
    }




}
