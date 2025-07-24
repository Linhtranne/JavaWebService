package com.data.session12.service.role;

import com.data.session12.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final com.data.session12.repo.RoleRepository roleRepository;

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
    }

    @Override
    public Set<Role> getDefaultRoles() {
        return Set.of(findByName("USER"));
    }
}
