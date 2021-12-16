package com.example.sharelockapi.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "houseshare", schema = "shareloc")
public class HouseshareEntity {

    private int id;
    private String name;
    private String description;
    private Date dateCreation;

    public HouseshareEntity(int id, String name, String description, Date dateCreation){
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreation = dateCreation;
    }

    public HouseshareEntity() {

    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "dateCreation")
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseshareEntity that = (HouseshareEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dateCreation != null ? !dateCreation.equals(that.dateCreation) : that.dateCreation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        return result;
    }
}
