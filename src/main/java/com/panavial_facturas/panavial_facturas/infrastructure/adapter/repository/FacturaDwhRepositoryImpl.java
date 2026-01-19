package com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository;

import com.panavial_facturas.panavial_facturas.domain.model.Factura;
import com.panavial_facturas.panavial_facturas.domain.port.repository.FacturaRepositoryPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        String rucFijo = "9999999999999";
        StringBuilder sql = new StringBuilder("""
        SELECT
            ff.fecha_emision,
            ff.establecimiento,
            ff.autorizacion,
            ff.numero_transito,
            ff.total
        FROM dm_facturacion.dim_cliente dc
        INNER JOIN dm_facturacion.fact_facturas_old ff
            ON ff.id_dim_cliente = dc.id_dim_cliente
        WHERE 1=1
          AND dc.ruc_cliente = ?
    """);

        // Lista de parámetros
        List<Object> params = new ArrayList<>();
        params.add(rucFijo); // RUC fijo

        if (fecha != null) {
            sql.append(" AND ff.fecha_emision = ?");
            params.add(fecha);
        }

        sql.append(" LIMIT 10");
        return jdbcTemplate.query(
                sql.toString(),
                facturaRowMapper(),
                params.toArray()
        );
    }




    private RowMapper<Factura> facturaRowMapper() {
        return (rs, rowNum) -> new Factura(
                rs.getTimestamp("fecha_emision").toLocalDateTime(),
                rs.getString("establecimiento"),
                rs.getString("autorizacion"),
                rs.getString("numero_transito"),
                rs.getBigDecimal("total")
        );
    }
}

