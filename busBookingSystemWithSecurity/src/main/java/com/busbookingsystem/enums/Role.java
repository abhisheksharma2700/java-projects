package com.busbookingsystem.enums;

import java.util.Set;

public enum Role {
    ROLE_ADMIN(Set.of(Permissions.READ,Permissions.WRITE,Permissions.DELETE)),
    ROLE_USER(Set.of(Permissions.READ));


    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
    public Set<Permissions>getPermissions(){
        return permissions;
    }
}
