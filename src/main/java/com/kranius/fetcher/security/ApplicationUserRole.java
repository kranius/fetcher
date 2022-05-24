package com.kranius.fetcher.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.kranius.fetcher.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(USER_READ)),
    ADMIN(Sets.newHashSet(PRODUCT_READ, PRODUCT_WRITE, USER_READ, USER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
