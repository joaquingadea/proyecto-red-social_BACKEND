package com.red_social.demo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    
    // Relacion N a N / roles -> permisos unidireccional
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission"
    ,joinColumns = @JoinColumn(name = "role_id")
    ,inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissionList= new HashSet<>();

    public Role(String name, Set<Permission> permissionList) {
        this.name = name;
        this.permissionList = permissionList;
    }
}
