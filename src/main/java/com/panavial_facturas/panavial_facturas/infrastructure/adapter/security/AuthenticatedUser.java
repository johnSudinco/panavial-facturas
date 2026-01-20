package com.panavial_facturas.panavial_facturas.infrastructure.adapter.security;

public record AuthenticatedUser(
        Long userId,
        String identification
) {}
