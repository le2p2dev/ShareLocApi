package com.example.sharelockapi.controllers;

import com.example.sharelockapi.dao.DAOTask;
import com.example.sharelockapi.dao.DAOUserHasHouseShare;
import com.example.sharelockapi.model.*;

import java.util.ArrayList;
import java.util.List;

public class UserHasHouseShareManager {

    static DAOUserHasHouseShare daoUserHasHouseShare = new DAOUserHasHouseShare();

    public static List<UserHasHouseshareEntity> getUserHasHouseShares() {
        List<UserHasHouseshareEntity> ct = daoUserHasHouseShare.findAll();
        return ct;
    }

    public static UserHasHouseshareEntity getUserHouseShareById(int id) {
        if (id < 0)
            return null;

        UserHasHouseshareEntity u = daoUserHasHouseShare.find(id);
        return u;
    }

    public static List<UserHasHouseshareEntity> getUserHousShareByUserId(int id){
        if(id <0){
            return null;
        }
        List<UserHasHouseshareEntity> list = new ArrayList<>();
        list = DAOUserHasHouseShare.getUserHousShareByUserId(id);
        return list;
    }



    public static boolean createUserHasHouseShare(int id,UserEntity user, HouseshareEntity houseshare, int points, int isOwner) {
        try{
            UserHasHouseshareEntity h = new UserHasHouseshareEntity(id,user.getId(),houseshare.getId(),points,isOwner);
            DAOUserHasHouseShare.create(h);
        }catch (Error e){
            System.out.println(e);
            return false;
        }
        return true;
    }

}
