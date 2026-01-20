package com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository.postgres;

import com.panavial_facturas.panavial_facturas.domain.model.postgres.Factura;
import com.panavial_facturas.panavial_facturas.infrastructure.adapter.entities.postgres.FacturaEntity;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {

    public Factura toDomain(FacturaEntity entity) {
        return new Factura(
                entity.getFechaEmision(),
                entity.getFechaSri(),
                entity.getIdFactFacturas(),
                entity.getEstablecimiento(),
                entity.getAutorizacion(),
                entity.getNumeroTransito(),
                entity.getTotal()
        );
    }

    public FacturaEntity toEntity(Factura factura) {
        FacturaEntity entity = new FacturaEntity();
        entity.setIdFactFacturas(factura.getIdFactFacturas());
        entity.setFechaEmision(factura.getFechaEmision());
        entity.setFechaSri(factura.getFechaSri());
        entity.setEstablecimiento(factura.getEstablecimiento());
        entity.setAutorizacion(factura.getAutorizacion());
        entity.setNumeroTransito(factura.getNumeroTransito());
        entity.setTotal(factura.getTotal());
        return entity;
    }
}