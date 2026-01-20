package com.panavial_facturas.panavial_facturas.domain.model.mysql;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Saldos {

    private int tracli;
    private String tranombre;
    private String tradocumento;

    private LocalDate trafecha;
    private LocalTime trahora;

    private LocalDate trafectran;
    private LocalTime trahoratra;

    private String traestacion;
    private String travia;
    private String tratoperacion;

    private String traplaca;
    private String tradispositivo;
    private String tracategoria;
    private String tralecman;
    private String traval;

    private BigDecimal trasaldo;
    private String trafactu;

    // Getters y Setters
    public int getTracli() { return tracli; }
    public void setTracli(int tracli) { this.tracli = tracli; }

    public String getTranombre() { return tranombre; }
    public void setTranombre(String tranombre) { this.tranombre = tranombre; }

    public String getTradocumento() { return tradocumento; }
    public void setTradocumento(String tradocumento) { this.tradocumento = tradocumento; }

    public LocalDate getTrafecha() { return trafecha; }
    public void setTrafecha(LocalDate trafecha) { this.trafecha = trafecha; }

    public LocalTime getTrahora() { return trahora; }
    public void setTrahora(LocalTime trahora) { this.trahora = trahora; }

    public LocalDate getTrafectran() { return trafectran; }
    public void setTrafectran(LocalDate trafectran) { this.trafectran = trafectran; }

    public LocalTime getTrahoratra() { return trahoratra; }
    public void setTrahoratra(LocalTime trahoratra) { this.trahoratra = trahoratra; }

    public String getTraestacion() { return traestacion; }
    public void setTraestacion(String traestacion) { this.traestacion = traestacion; }

    public String getTravia() { return travia; }
    public void setTravia(String travia) { this.travia = travia; }

    public String getTratoperacion() { return tratoperacion; }
    public void setTratoperacion(String tratoperacion) { this.tratoperacion = tratoperacion; }

    public String getTraplaca() { return traplaca; }
    public void setTraplaca(String traplaca) { this.traplaca = traplaca; }

    public String getTradispositivo() { return tradispositivo; }
    public void setTradispositivo(String tradispositivo) { this.tradispositivo = tradispositivo; }

    public String getTracategoria() { return tracategoria; }
    public void setTracategoria(String tracategoria) { this.tracategoria = tracategoria; }

    public String getTralecman() { return tralecman; }
    public void setTralecman(String tralecman) { this.tralecman = tralecman; }

    public String getTraval() { return traval; }
    public void setTraval(String traval) { this.traval = traval; }

    public BigDecimal getTrasaldo() { return trasaldo; }
    public void setTrasaldo(BigDecimal trasaldo) { this.trasaldo = trasaldo; }

    public String getTrafactu() { return trafactu; }
    public void setTrafactu(String trafactu) { this.trafactu = trafactu; }
}
