package com.panavial_facturas.panavial_facturas.application.dto.postgres;

import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FacturaResponse(
        LocalDateTime fechaEmision,
        LocalDateTime fechaSri,
        int idFactFacturas,
        String establecimiento,
        String autorizacion,
        String numeroTransito,
        BigDecimal total
) {

    public static FacturaResponse fromDomain(Factura factura) {
        return new FacturaResponse(
                factura.getFechaEmision(),
                factura.getFechaSri(),
                factura.getIdFactFacturas(),
                factura.getEstablecimiento(),
                factura.getAutorizacion(),
                factura.getNumeroTransito(),
                factura.getTotal()
        );
    }
}
