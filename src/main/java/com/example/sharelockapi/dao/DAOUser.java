package com.example.sharelockapi.dao;


import com.example.sharelockapi.model.UserEntity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DAOUser {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static EntityManager em;

    public static EntityManager getEntityManager() {

        return emf.createEntityManager();
    }

    /**
     * To create a new user use this method
     */
    public static UserEntity create(UserEntity u) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        EntityManager manager = getEntityManager();

        transaction.begin();

        manager.persist(u);


        transaction.commit();
        return u;
    }

    /**
     * TO edit à user
     */
    public static UserEntity edit(UserEntity u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(u);
        getEntityManager().getTransaction().commit();

        return u;
    }


    /**
     * to remove an user
     */
    public static UserEntity remove(UserEntity u) {
        getEntityManager().remove(getEntityManager().merge(u));
        return u;
    }

    /**
     * find user by id
     */
    public UserEntity find(int id) {
        return getEntityManager().find(UserEntity.class, id);
    }

    public UserEntity findByName(String login) {
        //get UserEntity by name ->
        Query query = getEntityManager().createQuery(
                "SELECT u.id FROM UserEntity u WHERE u.login like '" + login + "' ", UserEntity.class
        );
        List list = query.getResultList();
        int u = (int) list.get(0);

        return getEntityManager().find(UserEntity.class, u);
    }

    /**
     * find all users
     */
    public ArrayList<UserEntity> findAll() {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = criteriaBuilder.createQuery(UserEntity.class);
        cq.select(cq.from(UserEntity.class));
        Vector<UserEntity> v = (Vector<UserEntity>) getEntityManager().createQuery(cq).getResultList();
        if (v != null)
            return new ArrayList<UserEntity>(v);
        return null;

    }


}
