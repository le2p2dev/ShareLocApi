package com.example.sharelockapi.controllers;

import com.example.sharelockapi.dao.DAOCategory;
import com.example.sharelockapi.dao.DAOHouseShare;
import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.model.HouseshareEntity;

import java.util.Date;
import java.util.List;

public class HouseShareManager {

    static DAOHouseShare houseshare = new DAOHouseShare();

    public static List<HouseshareEntity> getHouseShares() {
        List<HouseshareEntity> ct = houseshare.findAll();
        return ct;
    }

    public static HouseshareEntity getHouseShareById(int id) {
        if (id < 0)
            return null;

        HouseshareEntity u = houseshare.find(id);
        return u;
    }


    public static void delete (HouseshareEntity houseshare){
        try{
            DAOHouseShare.delete(houseshare);
        }catch (Error error){
            System.out.println(error);
        }

    }



    public static boolean createHouseShare(String name,String description) {
        //get id ->
        int id = getHouseShares().size();

        //création de l'objet date
        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);


        HouseshareEntity u = houseshare.find(id);
        if (u == null) {
            DAOHouseShare.create(new HouseshareEntity(id,name,description, date1));
            return true;
        }
        return false;
    }

}
