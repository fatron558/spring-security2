package web.dao;

import web.models.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> getAll();

    Role getRole(long id);

    Role findRoleByName(String roleName);
}
