package com.edomew.docker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false,unique = true)
    @Min(1)
    @Max(12)
    private Integer roleId;
    @Column(name = "role_name", unique = true, nullable = false)
    private String authority;
    public Role() {
    }
    public String getRole() {
        return getAuthority().replaceAll("ROLE_","");
    }

}
