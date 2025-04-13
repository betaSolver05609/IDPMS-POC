package com.example.idpms.Repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.idpms.Model.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepo {
    @PersistenceContext
    EntityManager entityManager;
    
    public void persistUser(BigInteger generator, BigInteger prime, String username, BigInteger x, BigInteger b, BigInteger k) {
        Users user = new Users(username, generator, prime, x, b, k);
        entityManager.persist(user);
    }

    public Users fetchUserByUsername(String username) {
        TypedQuery<Users> query = entityManager.createNamedQuery("fetch_user_by_username", Users.class);
        query.setParameter("username", username);
        List<Users> users = query.getResultList();
        return users.get(0);
    }
}
