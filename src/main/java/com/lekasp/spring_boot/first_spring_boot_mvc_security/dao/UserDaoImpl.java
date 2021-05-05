package com.lekasp.spring_boot.first_spring_boot_mvc_security.dao;

import com.lekasp.spring_boot.first_spring_boot_mvc_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        if (id != null) {
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResult());
        }
        throw new NoResultException("No User by: " + id + " present");
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        User user = query.getResultList().stream().findAny().orElse(null);
        entityManager.remove(user);
    }

    @Override
    public Optional<User> getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u JOIN FETCH u.roles where u.name = :name", User.class);
        if (name != null) {
            query.setParameter("name", name);
            return Optional.ofNullable(query.getSingleResult());
        }
        throw new NoResultException("No User by: " + name + " present");
    }
}