package com.panavial_facturas.panavial_facturas.infrastructure.adapter.entities.postgres;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fact_facturas")
public class FacturaEntity {

    @Id
    @Column(name = "id_dim_cliente")
    private Integer idDimCliente;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "fecha_sri")
    private LocalDateTime fechaSri;

    @Column(name = "establecimiento")
    private String establecimiento;

    @Column(name = "autorizacion")
    private String autorizacion;

    @Column(name = "numero_transito")
    private String numeroTransito;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "punto_emision")
    private String puntoEmision;

    @Column(name = "clave_acceso")
    private String claveAcceso;

    @Column(name = "numero_secuencial")
    private Integer numeroSecuencial;

    @Column(name = "id_dim_peaje")
    private Integer idDimPeaje;

    @Column(name = "id_dim_concesion")
    private Integer idDimConcesion;
}