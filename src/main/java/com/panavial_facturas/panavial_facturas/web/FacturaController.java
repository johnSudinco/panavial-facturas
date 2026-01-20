package com.panavial_facturas.panavial_facturas.web;

import com.panavial_facturas.panavial_facturas.application.dto.postgres.FacturaResponse;
import com.panavial_facturas.panavial_facturas.application.usecase.postgres.GetFacturasUseCase;
import com.panavial_facturas.panavial_facturas.infrastructure.adapter.security.AuthenticatedUser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha,
            Authentication authentication
    ) {
        if (authentication == null ||
                !(authentication.getPrincipal() instanceof AuthenticatedUser user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            if (fecha == null) {
                return ResponseEntity
                        .badRequest()
                        .body(List.of()); // o mensaje de error
            }

            return ResponseEntity.ok(
                    useCase.execute(null, fecha)
            );
        }

        // USER
        return ResponseEntity.ok(
                useCase.execute(user.identification(), fecha)
        );
    }


}
