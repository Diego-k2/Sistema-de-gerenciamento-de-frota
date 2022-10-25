package com.diego.Sistema.gerenciamento.de.frota.model.entity;

import com.diego.Sistema.gerenciamento.de.frota.model.enums.Roles;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class RoleModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private Roles role;


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return String.valueOf(this.role);
    }
}
