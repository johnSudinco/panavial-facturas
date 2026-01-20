package com.panavial_facturas.panavial_facturas.application.dto.mysql;

import com.panavial_facturas.panavial_facturas.domain.model.mysql.Saldos;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record SaldosResponse(
        int tracli,
        String tranombre,
        String tradocumento,
        LocalDate trafecha,
        LocalTime trahora,
        LocalDate trafectran,
        LocalTime trahoratra,
        String traestacion,
        String travia,
        String tratoperacion,
        String traplaca,
        String tradispositivo,
        String tracategoria,
        String tralecman,
        String traval,
        BigDecimal trasaldo,
        String trafactu
) {
    public static SaldosResponse fromDomain(Saldos saldos) {
        return new SaldosResponse(
                saldos.getTracli(),
                saldos.getTranombre(),
                saldos.getTradocumento(),
                saldos.getTrafecha(),
                saldos.getTrahora(),
                saldos.getTrafectran(),
                saldos.getTrahoratra(),
                saldos.getTraestacion(),
                saldos.getTravia(),
                saldos.getTratoperacion(),
                saldos.getTraplaca(),
                saldos.getTradispositivo(),
                saldos.getTracategoria(),
                saldos.getTralecman(),
                saldos.getTraval(),
                saldos.getTrasaldo(),
                saldos.getTrafactu()
        );
    }
}