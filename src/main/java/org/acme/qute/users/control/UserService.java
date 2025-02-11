package org.acme.qute.users.control;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.qute.users.entity.Users;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager em;

    public List<Users> getAll() {

        return em.createQuery("select u from Users u", Users.class).getResultList();
    }

}
