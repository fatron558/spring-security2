package web.service;

import web.models.Role;
import web.models.User;

import java.util.Set;

public interface RoleService {

    public Set<Role> getAll();

    Role getRole(long id);

    Role findRoleByName(String role);
}
