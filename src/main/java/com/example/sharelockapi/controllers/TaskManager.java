package com.example.sharelockapi.controllers;

import com.example.sharelockapi.dao.DAOCategory;
import com.example.sharelockapi.dao.DAOTask;
import com.example.sharelockapi.model.CategoryEntity;
import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.model.TaskEntity;

import java.util.List;

public class TaskManager {

    static DAOTask daoTask = new DAOTask();

    public static List<TaskEntity> getTasks() {
        List<TaskEntity> ct = daoTask.findAll();
        return ct;
    }

    public static TaskEntity getTaskById(int id) {
        if (id < 0)
            return null;

        TaskEntity u = daoTask.find(id);
        return u;
    }

    public static List<TaskEntity> getTasksByHouseShareId(int id){
        List<TaskEntity> ct = DAOTask.findByHouseShareId(id);
        return ct;
    }



    public static boolean createTask(int id, HouseshareEntity houseshare,CategoryEntity category,int points, String description,String title) {
        TaskEntity u = daoTask.find(id);
        if (u == null) {
            DAOTask.create(
                    new TaskEntity(id,houseshare.getId(),category.getId(),points,description,title)
            );
            return true;
        }
        return false;
    }

}
