package com.data.session12.service.role;


import com.data.session12.model.entity.Role;

import java.util.Set;

public interface RoleService {
    Role findByName(String roleName);
    Set<Role> getDefaultRoles();
}
