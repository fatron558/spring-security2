package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.models.Role;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao roleDao;

    @Override
    public Set<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getRole(long id) {
        return roleDao.getRole(id);
    }

    @Override
    public Role findRoleByName(String role) {
        return roleDao.findRoleByName(role);
    }


}
