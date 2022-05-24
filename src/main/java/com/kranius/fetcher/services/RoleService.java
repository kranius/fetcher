package com.kranius.fetcher.services;

import com.kranius.fetcher.exceptions.IdentityNotFoundException;
import com.kranius.fetcher.exceptions.NonUniqueRoleNameException;
import com.kranius.fetcher.models.Role;
import com.kranius.fetcher.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public boolean roleExists(String name) {
        return roleRepository.findByName(name).isEmpty();
    }

    public Role createRole(Role role) throws NonUniqueRoleNameException {
        if (roleExists(role.getName()))
            throw new NonUniqueRoleNameException("Role " + role.getName() + " already exists");
        return roleRepository.save(role);
    }

    public void deleteRole(long id) throws IdentityNotFoundException {
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> new IdentityNotFoundException("Could not find Role id " + id));

        roleRepository.delete(role);
    }

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
