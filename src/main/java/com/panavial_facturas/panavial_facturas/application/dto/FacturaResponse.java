package com.panavial_facturas.panavial_facturas.application.dto;

import com.panavial_facturas.panavial_facturas.domain.model.Factura;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FacturaResponse(
        LocalDateTime fechaEmision,
        String establecimiento,
        String autorizacion,
        String numeroTransito,
        BigDecimal total
) {

    public static FacturaResponse fromDomain(Factura factura) {
        return new FacturaResponse(
                factura.getFechaEmision(),
                factura.getEstablecimiento(),
                factura.getAutorizacion(),
                factura.getNumeroTransito(),
                factura.getTotal()
        );
    }
}
