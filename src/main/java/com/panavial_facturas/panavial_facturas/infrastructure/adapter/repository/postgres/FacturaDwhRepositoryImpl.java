package com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository.postgres;

import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;
import com.panavial_facturas.panavial_facturas.domain.port.repository.postgres.FacturaRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public FacturaDwhRepositoryImpl(@Qualifier("postgresJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Factura> findByRucAndFecha(String ruc, LocalDate fecha) {

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
            ff.total,
            ff.punto_emision,
            ff.clave_acceso,
            ff.numero_secuencial,
            ff.id_dim_peaje,
            ff.id_dim_concesion
        FROM dm_facturacion.dim_cliente dc
        INNER JOIN dm_facturacion.fact_facturas_old ff
            ON ff.id_dim_cliente = dc.id_dim_cliente
        WHERE dc.ruc_cliente = ?
    """);

        List<Object> params = new ArrayList<>();
        params.add(ruc);

        if (fecha != null) {
            LocalDateTime start = fecha.atStartOfDay();
            LocalDateTime end = start.plusDays(1);
            sql.append(" AND ff.fecha_emision >= ? AND ff.fecha_emision < ?");
            params.add(start);
            params.add(end);
        }

        sql.append(" ORDER BY ff.fecha_emision DESC");

        return jdbcTemplate.query(
                sql.toString(),
                facturaRowMapper(),
                params.toArray()
        );
    }

    @Override
    public List<Factura> findByFecha(LocalDate fecha) {

        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha es obligatoria para consultas ADMIN"
            );
        }

        String sql = """
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
            ff.total,
            ff.punto_emision,
            ff.clave_acceso,
            ff.numero_secuencial,
            ff.id_dim_peaje,
            ff.id_dim_concesion
        FROM dm_facturacion.dim_cliente dc
        INNER JOIN dm_facturacion.fact_facturas_old ff
            ON ff.id_dim_cliente = dc.id_dim_cliente
        WHERE ff.fecha_emision >= ?
          AND ff.fecha_emision < ?
        ORDER BY ff.fecha_emision DESC
    """;

        LocalDateTime start = fecha.atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        return jdbcTemplate.query(
                sql,
                facturaRowMapper(),
                start,
                end
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
                    rs.getBigDecimal("total"),
                    rs.getString("punto_emision"),
                    rs.getString("clave_acceso"),
                    rs.getInt("numero_secuencial"),
                    rs.getInt("id_dim_peaje"),
                    rs.getInt("id_dim_concesion")
            );
        };
    }

}

