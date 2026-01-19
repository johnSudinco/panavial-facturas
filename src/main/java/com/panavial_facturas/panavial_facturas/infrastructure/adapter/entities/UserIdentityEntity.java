package com.panavial_facturas.panavial_facturas.infrastructure.adapter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_identity")
public class UserIdentityEntity {

    @Id
    private Long userId;

    @Column(nullable = false, length = 20)
    private String cedula;
}
