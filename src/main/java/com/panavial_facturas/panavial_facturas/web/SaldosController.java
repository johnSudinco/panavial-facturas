package com.panavial_facturas.panavial_facturas.web;

import com.panavial_facturas.panavial_facturas.application.dto.mysql.SaldosResponse;
import com.panavial_facturas.panavial_facturas.application.dto.postgres.FacturaResponse;
import com.panavial_facturas.panavial_facturas.application.usecase.mysql.GetSaldosUseCase;
import com.panavial_facturas.panavial_facturas.application.usecase.postgres.GetFacturasUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/saldos")
public class SaldosController {

    private final GetSaldosUseCase useCase;

    public SaldosController(GetSaldosUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<SaldosResponse>> misSaldos(
            @RequestParam(required = false) String ruc,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        return ResponseEntity.ok(
                useCase.execute(ruc, fechaInicio, fechaFin)
        );
    }

}
