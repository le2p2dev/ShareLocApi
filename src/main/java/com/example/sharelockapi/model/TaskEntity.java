package com.example.sharelockapi.model;

import javax.persistence.*;

@Entity
@Table(name = "Task", schema = "shareloc", catalog = "")
public class TaskEntity {
    private int id;
    private int houseShareId;
    private int categoryId;
    private Integer points;
    private String description;
    private String title;

    public TaskEntity(int id, int houseShareId, int categoryId, Integer points, String description, String title) {
        this.id = id;
        this.houseShareId = houseShareId;
        this.categoryId = categoryId;
        this.points = points;
        this.description = description;
        this.title = title;
    }

    public TaskEntity() {

    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "houseshare_id")
    public int getHouseShareId(){
        return houseShareId;
    }
    public void setHouseShareId(int houseShareId){
        this.houseShareId = houseShareId;
    }


    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    @Basic
    @Column(name = "points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (id != that.id) return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
