package com.example.sharelockapi.dao;


import com.example.sharelockapi.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Vector;

public class DAOUser {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static  EntityManager em;

    public static EntityManager getEntityManager(){

        return emf.createEntityManager();
    }

    /**
     * To create a new user use this method
     *
     */
    public static UserEntity create(UserEntity u){
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(u);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
        return u;
    }

    /**
     *  TO edit à user
     */
    public static UserEntity edit(UserEntity u){
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(u);
        getEntityManager().getTransaction().commit();

        return u;
    }


    /**
     * to remove an user
     */
    public static UserEntity remove(UserEntity u){
        getEntityManager().remove(getEntityManager().merge(u));
        return u;
    }

    /**
     * find user by id
     */
    public UserEntity find(int id) {
        return getEntityManager().find(UserEntity.class, id);
    }

    public UserEntity findByName(String name){
        return getEntityManager().find(UserEntity.class,name);
    }

    /**
     * find all users
     */
    public ArrayList<UserEntity> findAll() {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = criteriaBuilder.createQuery(UserEntity.class);
        cq.select(cq.from(UserEntity.class));
        Vector<UserEntity> v = (Vector<UserEntity>) getEntityManager().createQuery(cq).getResultList();
        if ( v != null)
            return new ArrayList<UserEntity>(v);
        return null;

    }





}
