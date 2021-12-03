package com.example.sharelockapi.dao;

import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.model.HouseshareEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Vector;

public class DAOHouseShare {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static EntityManager em;

    public static EntityManager getEntityManager() {

        return emf.createEntityManager();
    }

    /**
     * To create a new houseshare use this method
     */
    public static HouseshareEntity create(HouseshareEntity u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(u);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
        return u;
    }

    /**
     * TO edit à houseshare
     */
    public static HouseshareEntity edit(HouseshareEntity u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(u);
        getEntityManager().getTransaction().commit();

        return u;
    }


    /**
     * to remove an houseshare
     */
    public static HouseshareEntity remove(HouseshareEntity u) {
        getEntityManager().remove(getEntityManager().merge(u));
        return u;
    }

    /**
     * find house by id
     */
    public HouseshareEntity find(int id) {
        return getEntityManager().find(HouseshareEntity.class, id);
    }

    public HouseshareEntity findByName(String name) {
        return getEntityManager().find(HouseshareEntity.class, name);
    }

    /**
     * find all houseshare
     */
    public ArrayList<HouseshareEntity> findAll() {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<HouseshareEntity> cq = criteriaBuilder.createQuery(HouseshareEntity.class);
        cq.select(cq.from(HouseshareEntity.class));
        Vector<HouseshareEntity> v = (Vector<HouseshareEntity>) getEntityManager().createQuery(cq).getResultList();
        if (v != null)
            return new ArrayList<HouseshareEntity>(v);
        return null;

    }


}

