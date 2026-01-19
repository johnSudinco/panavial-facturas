package com.panavial_facturas.panavial_facturas.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Factura {

    private final LocalDateTime fechaEmision;
    private final LocalDateTime fechaSri;
    private final int idFactFacturas;
    private final String establecimiento;
    private final String autorizacion;
    private final String numeroTransito;
    private final BigDecimal total;

    public Factura(
            LocalDateTime fechaEmision,
            LocalDateTime fechaSri,
            int idFactFacturas,
            String establecimiento,
            String autorizacion,
            String numeroTransito,
            BigDecimal total
    ) {
        this.fechaEmision = fechaEmision;
        this.fechaSri = fechaSri;
        this.idFactFacturas = idFactFacturas;
        this.establecimiento = establecimiento;
        this.autorizacion = autorizacion;
        this.numeroTransito = numeroTransito;
        this.total = total;
    }


    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public LocalDateTime getFechaSri() { return fechaSri; }
    public String getEstablecimiento() { return establecimiento; }
    public int getIdFactFacturas() { return idFactFacturas; }
    public String getAutorizacion() { return autorizacion; }
    public String getNumeroTransito() { return numeroTransito; }
    public BigDecimal getTotal() { return total; }
}
