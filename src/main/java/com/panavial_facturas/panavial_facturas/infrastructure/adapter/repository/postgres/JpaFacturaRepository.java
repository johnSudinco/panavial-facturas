package com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository.postgres;

import com.panavial_facturas.panavial_facturas.infrastructure.adapter.entities.postgres.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFacturaRepository extends JpaRepository<FacturaEntity, Integer> {
}