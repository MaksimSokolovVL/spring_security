package com.dhabits.ss.demo.utils;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class JoinedRolesUtil {
    private JoinedRolesUtil() {
    }

    public static String joinRoles(ResourceObject user){
        List<String> roles = new ArrayList<>();
        for (RoleEntity role : user.getRoles()) {
            roles.add(role.getRoleName().replace("ROLE_", ""));
        }
        return String.join(", ", roles);
    }
}
