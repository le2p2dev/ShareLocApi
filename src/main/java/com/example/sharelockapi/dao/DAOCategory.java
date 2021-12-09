package com.example.sharelockapi.dao;

import com.example.sharelockapi.controllers.CategoryManager;
import com.example.sharelockapi.model.CategoryEntity;
import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.model.UserEntity;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Vector;

public class DAOCategory {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default");
    private static EntityManager em;



    public static EntityManager getEntityManager() {
        if (em == null){
            em = emf.createEntityManager();
        }
        return em;
    }

    // TODO : create crud operations

    /**
     * Create
     */
    public static CategoryEntity create(CategoryEntity category){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        getEntityManager().persist(category);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();

        return category;
    }


    /**
     * find by id
     */
    public static CategoryEntity findById(int id){
        return getEntityManager().find(CategoryEntity.class,id);
    }

    public CategoryEntity find(int id) {
        return getEntityManager().find(CategoryEntity.class, id);
    }


    /**
     * find all
     */
    public ArrayList<CategoryEntity> findAll() {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> cq = criteriaBuilder.createQuery(CategoryEntity.class);
        cq.select(cq.from(CategoryEntity.class));
        Vector<CategoryEntity> v = (Vector<CategoryEntity>) getEntityManager().createQuery(cq).getResultList();
        if ( v != null)
            return new ArrayList<CategoryEntity>(v);
        return null;

    }

    /**
     * Edit
     */
    public static CategoryEntity edit(CategoryEntity category){
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(category);
        getEntityManager().getTransaction().commit();

        return category;
    }

    /**
     * Delete
     */
    public static CategoryEntity remove(CategoryEntity category){
        getEntityManager().remove(getEntityManager().merge(category));
        return category;
    }


}
