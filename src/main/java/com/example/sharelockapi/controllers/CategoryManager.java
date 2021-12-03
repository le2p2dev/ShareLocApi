package com.example.sharelockapi.controllers;

import com.example.sharelockapi.dao.DAOCategory;
import com.example.sharelockapi.model.CategoryEntity;

import java.util.List;

public class CategoryManager {

    static DAOCategory daoCategory = new DAOCategory();

    public static List<CategoryEntity> getCategories() {
        List<CategoryEntity> ct = daoCategory.findAll();
        return ct;
    }

    public static CategoryEntity getCategoryById(int id) {
        if (id < 0)
            return null;

        CategoryEntity u = daoCategory.find(id);
        return u;
    }



    public static boolean createCategoriy(int id,String label) {
        CategoryEntity u = daoCategory.find(id);
        if (u == null) {
            DAOCategory.create(new CategoryEntity(id,label));
            return true;
        }
        return false;
    }

}
