package com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository.mysql;

import com.panavial_facturas.panavial_facturas.domain.model.mysql.Saldos;
import com.panavial_facturas.panavial_facturas.domain.port.repository.mysql.SaldosRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SaldosRepositoryImpl implements SaldosRepositoryPort {

    private final JdbcTemplate jdbcTemplate;

    public SaldosRepositoryImpl(@Qualifier("mysqlJdbcTemplate")JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Saldos> findByRucAndFechaRange(String ruc, LocalDate fechaInicio, LocalDate fechaFin) {
        StringBuilder sql = new StringBuilder("""
        SELECT 
            tracli, tranombre, tradocumento,
            trafecha, trahora,
            trafectran, trahoratra, traestacion, travia, tratoperacion,
            traplaca, tradispositivo, tracategoria, tralecman, traval,
            trasaldo, trafactu
        FROM consultaWeb.transac
        WHERE tradocumento = ?
    """);

        List<Object> params = new ArrayList<>();
        params.add(ruc);

        if (fechaInicio != null && fechaFin != null) {
            sql.append(" AND trafecha BETWEEN ? AND ?");
            params.add(fechaInicio);
            params.add(fechaFin);
        } else if (fechaInicio != null) {
            sql.append(" AND trafecha >= ?");
            params.add(fechaInicio);
        } else if (fechaFin != null) {
            sql.append(" AND trafecha <= ?");
            params.add(fechaFin);
        }

        sql.append(" ORDER BY trafecha, trahora LIMIT 100");

        return jdbcTemplate.query(
                sql.toString(),
                saldosRowMapper(),
                params.toArray()
        );
    }
    @Override
    public List<Saldos> findByFechaRange(
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException(
                    "Las fechas son obligatorias para consultas ADMIN"
            );
        }

        String sql = """
        SELECT 
            tracli, tranombre, tradocumento,
            trafecha, trahora,
            trafectran, trahoratra, traestacion, travia, tratoperacion,
            traplaca, tradispositivo, tracategoria, tralecman, traval,
            trasaldo, trafactu
        FROM consultaWeb.transac
        WHERE trafecha BETWEEN ? AND ?
        LIMIT 10000
    """;

        return jdbcTemplate.query(
                sql,
                saldosRowMapper(),
                fechaInicio,
                fechaFin
        );
    }

    private RowMapper<Saldos> saldosRowMapper() {
        return (rs, rowNum) -> {
            Saldos s = new Saldos();
            s.setTracli(rs.getInt("tracli"));
            s.setTranombre(rs.getString("tranombre"));
            s.setTradocumento(rs.getString("tradocumento"));

            Date fechaSql = rs.getDate("trafecha");
            if (fechaSql != null) {
                s.setTrafecha(fechaSql.toLocalDate());
            }

            Time horaSql = rs.getTime("trahora");
            if (horaSql != null) {
                s.setTrahora(horaSql.toLocalTime());
            }

            Date fechaTranSql = rs.getDate("trafectran");
            if (fechaTranSql != null) {
                s.setTrafectran(fechaTranSql.toLocalDate());
            }

            Time horaTranSql = rs.getTime("trahoratra");
            if (horaTranSql != null) {
                s.setTrahoratra(horaTranSql.toLocalTime());
            }

            s.setTraestacion(rs.getString("traestacion"));
            s.setTravia(rs.getString("travia"));
            s.setTratoperacion(rs.getString("tratoperacion"));
            s.setTraplaca(rs.getString("traplaca"));
            s.setTradispositivo(rs.getString("tradispositivo"));
            s.setTracategoria(rs.getString("tracategoria"));
            s.setTralecman(rs.getString("tralecman"));
            s.setTraval(rs.getString("traval"));
            s.setTrasaldo(rs.getBigDecimal("trasaldo"));
            s.setTrafactu(rs.getString("trafactu"));

            return s;
        };
    }
}