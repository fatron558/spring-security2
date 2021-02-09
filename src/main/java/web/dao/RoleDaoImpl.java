package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Role> getAll() {
        List<Role> roles = em.createQuery("from Role", Role.class).getResultList();
        Set<Role> rolesSet = new HashSet<>();
        for (Role role : roles) {
            rolesSet.add(role);
        }
        return rolesSet;
    }

    @Override
    public Role getRole(long id) {
        return em.find(Role.class, id);
    }


}
