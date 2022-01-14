package com.example.sharelockapi.dao;

import com.example.sharelockapi.controllers.TaskManager;
import com.example.sharelockapi.controllers.UserHasHouseShareManager;
import com.example.sharelockapi.model.TaskEntity;
import com.example.sharelockapi.model.UserHasHouseshareEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DAOTask {

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
    public static TaskEntity create(TaskEntity u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(u);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
        return u;
    }

    /**
     * TO edit à task
     */
    public static TaskEntity edit(TaskEntity u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(u);
        getEntityManager().getTransaction().commit();

        return u;
    }

    /**
     * Get tasks by houseshare id
     */
    public static List<TaskEntity> findByHouseShareId(int id){
        Query query = getEntityManager().createQuery(
                "SELECT t.id from TaskEntity t WHERE t.houseShareId =" + id,TaskEntity.class);

        List<TaskEntity> list = new ArrayList<>();
        List list1 = query.getResultList();
        for (Object id1 : list1) {
            list.add(TaskManager.getTaskById((int) id1));
        }
        return list;

    }

    /**
     * to remove an task
     */
    public static TaskEntity remove(TaskEntity u) {
        getEntityManager().remove(getEntityManager().merge(u));
        return u;
    }

    /**
     * find task by id
     */
    public TaskEntity find(int id) {
        return getEntityManager().find(TaskEntity.class, id);
    }

    public TaskEntity findByName(String name) {
        return getEntityManager().find(TaskEntity.class, name);
    }

    /**
     * find all task
     */
    public ArrayList<TaskEntity> findAll() {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TaskEntity> cq = criteriaBuilder.createQuery(TaskEntity.class);
        cq.select(cq.from(TaskEntity.class));
        Vector<TaskEntity> v = (Vector<TaskEntity>) getEntityManager().createQuery(cq).getResultList();
        if (v != null)
            return new ArrayList<TaskEntity>(v);
        return null;

    }


}


