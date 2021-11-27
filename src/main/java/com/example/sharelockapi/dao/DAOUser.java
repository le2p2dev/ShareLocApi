package com.example.sharelockapi.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOUser {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = emf.createEntityManager();


    public static List<?> getAllUser(){
        // TODO : get list of flights using EntityManager
        return  em.createQuery("SELECT u FROM UserEntity u").getResultList();
    }

    public static List<?> getUserbyLogin(String login){
        // TODO : return list of flight using numVol attribute
        String requete = "SELECT u FROM UserEntity u WHERE u.login='"+login+"'";
        return em.createQuery(requete).getResultList();
    }



}
