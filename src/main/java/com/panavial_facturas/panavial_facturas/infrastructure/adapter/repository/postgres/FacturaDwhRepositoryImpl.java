package com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository.postgres;

import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;
import com.panavial_facturas.panavial_facturas.domain.port.repository.postgres.FacturaRepositoryPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FacturaDwhRepositoryImpl implements FacturaRepositoryPort {

    private final JdbcTemplate jdbcTemplate;

    public FacturaDwhRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Factura> findByRucAndFecha(String ruc, LocalDate fecha) {
        // RUC dinámico del front o RUC de prueba
        String rucToUse = (ruc != null && !ruc.isBlank()) ? ruc : "9999999999999";

        StringBuilder sql = new StringBuilder("""
        SELECT
            dc.id_dim_cliente,
            dc.ruc_cliente,
            dc.nombre_cliente,
            ff.id_fact_facturas,
            ff.fecha_emision,
            ff.establecimiento,
            ff.fecha_sri,
            ff.autorizacion,
            ff.numero_transito,
            ff.total
        FROM dm_facturacion.dim_cliente dc
        INNER JOIN dm_facturacion.fact_facturas_old ff
            ON ff.id_dim_cliente = dc.id_dim_cliente
        WHERE dc.ruc_cliente = ?
    """);

        List<Object> params = new ArrayList<>();
        params.add(rucToUse);

        if (fecha != null) {
            LocalDateTime startOfDay = fecha.atStartOfDay();         // 00:00:00
            LocalDateTime endOfDay = startOfDay.plusDays(1);        // siguiente día 00:00:00
            sql.append(" AND ff.fecha_emision >= ? AND ff.fecha_emision < ?");
            params.add(startOfDay);
            params.add(endOfDay);
        }

        sql.append(" LIMIT 50");

        return jdbcTemplate.query(
                sql.toString(),
                facturaRowMapper(),
                params.toArray()
        );
    }


    private RowMapper<Factura> facturaRowMapper() {
        return (rs, rowNum) -> {
            Timestamp fechaEmisionTs = rs.getTimestamp("fecha_emision");
            Timestamp fechaSriTs = rs.getTimestamp("fecha_sri");

            return new Factura(
                    fechaEmisionTs != null ? fechaEmisionTs.toLocalDateTime() : null,
                    fechaSriTs != null ? fechaSriTs.toLocalDateTime() : null,
                    rs.getInt("id_fact_facturas"),
                    rs.getString("establecimiento"),
                    rs.getString("autorizacion"),
                    rs.getString("numero_transito"),
                    rs.getBigDecimal("total")
            );
        };
    }

}

