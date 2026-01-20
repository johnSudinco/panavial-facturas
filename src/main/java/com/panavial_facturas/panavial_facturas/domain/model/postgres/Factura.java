package com.panavial_facturas.panavial_facturas.domain.model.postgres;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Factura {

    private final LocalDateTime fechaEmision;
    private final LocalDateTime fechaSri;
    private final int idFactFacturas;
    private final String establecimiento;
    private final String autorizacion;
    private final String numeroTransito;
    private final BigDecimal total;
    private final String puntoEmision;
    private final String claveAcceso;
    private final int numeroSecuencial;
    private final int idDimPeaje;
    private final int idDimConcesion;

    public Factura(
            LocalDateTime fechaEmision,
            LocalDateTime fechaSri,
            int idFactFacturas,
            String establecimiento,
            String autorizacion,
            String numeroTransito,
            BigDecimal total,
            String puntoEmision,
            String claveAcceso,
            int numeroSecuencial,
            int idDimPeaje,
            int idDimConcesion
    ) {
        this.fechaEmision = fechaEmision;
        this.fechaSri = fechaSri;
        this.idFactFacturas = idFactFacturas;
        this.establecimiento = establecimiento;
        this.autorizacion = autorizacion;
        this.numeroTransito = numeroTransito;
        this.total = total;
        this.puntoEmision = puntoEmision;
        this.claveAcceso = claveAcceso;
        this.numeroSecuencial = numeroSecuencial;
        this.idDimPeaje = idDimPeaje;
        this.idDimConcesion = idDimConcesion;
    }


    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public LocalDateTime getFechaSri() { return fechaSri; }
    public String getEstablecimiento() { return establecimiento; }
    public int getIdFactFacturas() { return idFactFacturas; }
    public String getAutorizacion() { return autorizacion; }
    public String getNumeroTransito() { return numeroTransito; }
    public BigDecimal getTotal() { return total; }
    public String getPuntoEmision() { return puntoEmision; }
    public String getClaveAcceso() { return claveAcceso; }
    public int getNumeroSecuencial() { return numeroSecuencial; }
    public int getIdDimPeaje() { return idDimPeaje; }
    public int getIdDimConcesion() { return idDimConcesion; }
}
