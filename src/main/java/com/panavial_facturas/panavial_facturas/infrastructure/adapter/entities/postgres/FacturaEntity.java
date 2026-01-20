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
    @Column(name = "id_fact_facturas")
    private Integer idFactFacturas;

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
}