package com.example.sharelockapi.controllers;



import java.util.List;

import com.example.sharelockapi.dao.DAOUser;
import com.example.sharelockapi.model.UserEntity;

public class UserManager {
    static DAOUser daoUser = new DAOUser();

    public static List<UserEntity> getUsers() {
        List<UserEntity> lv = daoUser.findAll();
        return lv;
    }

    public static UserEntity getUser(int id) {
        if (id < 0)
            return null;

        UserEntity u = daoUser.find(id);
        return u;
    }

    public static UserEntity getUser(String name) {
        if (name == null )
            return null;

        UserEntity u = daoUser.findByName(name);
        return u;
    }

    public static UserEntity login(int id,String login, String password) {
        UserEntity u = daoUser.find(id);
        if (u != null && u.getPassword().equals(password))
            return u;
        return null;
    }

    public static UserEntity login(String login, String password) {
        UserEntity u = daoUser.findByName(login);
        if (u != null && u.getPassword().equals(password))
            return u;
        return null;
    }

    public static boolean createUser(String login, String password, String firstname, String lastname,int id) {
        UserEntity u = daoUser.find(id);
        if (u == null) {
            DAOUser.create(new UserEntity(login, password, firstname, lastname,id));
            return true;
        }
        return false;
    }

}
