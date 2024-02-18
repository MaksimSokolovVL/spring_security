package com.dhabits.ss.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role", schema = "db_ss" )
public class RoleEntity implements GrantedAuthority {
    @Id
    private Long id;
    @Column(name = "role_name")
    private String roleName;

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    @Override
    public String toString() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity roleEntity = (RoleEntity) o;

        return roleName.equals(roleEntity.roleName);
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }
}
