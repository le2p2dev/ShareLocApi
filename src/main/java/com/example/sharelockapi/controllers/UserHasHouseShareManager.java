package com.example.sharelockapi.controllers;

import com.example.sharelockapi.dao.DAOTask;
import com.example.sharelockapi.dao.DAOUserHasHouseShare;
import com.example.sharelockapi.model.*;

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



    public static boolean createUserHasHouseShare(UserEntity user, HouseshareEntity houseshare, int points, int isOwner) {
        UserHasHouseshareEntity u = daoUserHasHouseShare.find(user.getId());
        if (u == null) {
            DAOUserHasHouseShare.create(
                    new UserHasHouseshareEntity(user.getId(),houseshare.getId(),points,isOwner)
            );
            return true;
        }
        return false;
    }

}
