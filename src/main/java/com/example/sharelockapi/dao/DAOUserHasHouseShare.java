package com.example.sharelockapi.dao;

import com.example.sharelockapi.model.UserHasHouseshareEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Vector;

public class DAOUserHasHouseShare {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        if (em == null){
            em = emf.createEntityManager();
        }
        return em;
    }

    /**
     * To create a new task use this method
     */
    public static UserHasHouseshareEntity create(UserHasHouseshareEntity u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(u);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
        return u;
    }

    /**
     * TO edit à task
     */
    public static UserHasHouseshareEntity edit(UserHasHouseshareEntity u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(u);
        getEntityManager().getTransaction().commit();

        return u;
    }


    /**
     * to remove an task
     */
    public static UserHasHouseshareEntity remove(UserHasHouseshareEntity u) {
        getEntityManager().remove(getEntityManager().merge(u));
        return u;
    }

    /**
     * find task by id
     */
    public UserHasHouseshareEntity find(int id) {
        return getEntityManager().find(UserHasHouseshareEntity.class, id);
    }

    public UserHasHouseshareEntity findByName(String name) {
        return getEntityManager().find(UserHasHouseshareEntity.class, name);
    }

    /**
     * find all task
     */
    public ArrayList<UserHasHouseshareEntity> findAll() {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserHasHouseshareEntity> cq = criteriaBuilder.createQuery(UserHasHouseshareEntity.class);
        cq.select(cq.from(UserHasHouseshareEntity.class));
        Vector<UserHasHouseshareEntity> v = (Vector<UserHasHouseshareEntity>) getEntityManager().createQuery(cq).getResultList();
        if (v != null)
            return new ArrayList<UserHasHouseshareEntity>(v);
        return null;

    }


}



