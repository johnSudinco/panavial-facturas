package com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository;

import com.panavial_facturas.panavial_facturas.infrastructure.adapter.entities.UserIdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserIdentityRepository
        extends JpaRepository<UserIdentityEntity, Long> {
}
