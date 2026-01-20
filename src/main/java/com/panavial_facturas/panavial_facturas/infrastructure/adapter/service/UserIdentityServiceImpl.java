package com.panavial_facturas.panavial_facturas.infrastructure.adapter.service;

import com.panavial_facturas.panavial_facturas.domain.port.service.UserIdentityService;
import com.panavial_facturas.panavial_facturas.infrastructure.adapter.entities.postgres.UserIdentityEntity;
import com.panavial_facturas.panavial_facturas.infrastructure.adapter.repository.postgres.JpaUserIdentityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityServiceImpl implements UserIdentityService {

    private final JpaUserIdentityRepository repository;

    public UserIdentityServiceImpl(JpaUserIdentityRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getCedulaByUserId(Long userId) {
        return repository.findById(userId)
                .map(UserIdentityEntity::getCedula)
                .orElseThrow(() ->
                        new RuntimeException("Identidad no encontrada")
                );
    }
}
