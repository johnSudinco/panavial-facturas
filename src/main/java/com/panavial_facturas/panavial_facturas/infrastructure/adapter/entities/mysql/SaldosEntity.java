package com.panavial_facturas.panavial_facturas.infrastructure.adapter.entities.mysql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "transac", schema = "consultaWeb")
public class SaldosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traid")
    private Integer id;

    @Column(name = "tracli")
    private int tracli;

    @Column(name = "tranombre")
    private String tranombre;

    @Column(name = "tradocumento")
    private String tradocumento;

    @Column(name = "trafecha")
    private LocalDate trafecha;

    @Column(name = "trahora")
    private LocalTime trahora;

    @Column(name = "trafectran")
    private LocalDate trafectran;

    @Column(name = "trahoratra")
    private LocalTime trahoratra;

    @Column(name = "traestacion")
    private String traestacion;

    @Column(name = "travia")
    private String travia;

    @Column(name = "tratoperacion")
    private String tratoperacion;

    @Column(name = "traplaca")
    private String traplaca;

    @Column(name = "tradispositivo")
    private String tradispositivo;

    @Column(name = "tracategoria")
    private String tracategoria;

    @Column(name = "tralecman")
    private String tralecman;

    @Column(name = "traval")
    private String traval;

    @Column(name = "trasaldo")
    private BigDecimal trasaldo;

    @Column(name = "trafactu")
    private String trafactu;

    // getters y setters...
}