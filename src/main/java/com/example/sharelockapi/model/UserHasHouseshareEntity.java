package com.example.sharelockapi.model;

import javax.persistence.*;

@Entity
@Table(name = "user_has_houseshare", schema = "shareloc")
public class UserHasHouseshareEntity {

    private Integer userId;
    private int Id;
    private Integer houseshareId;
    private Integer point;
    private Integer isOwner;

    public UserHasHouseshareEntity(int id,Integer userId, Integer houseshareId, Integer point, Integer isOwner) {
        this.Id = id;
        this.userId = userId;
        this.houseshareId = houseshareId;
        this.point = point;
        this.isOwner = isOwner;
    }

    public UserHasHouseshareEntity() {

    }

    @javax.persistence.Id
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }



    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "houseshare_id")
    public Integer getHouseshareId() {
        return houseshareId;
    }

    public void setHouseshareId(Integer houseshareId) {
        this.houseshareId = houseshareId;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "isOwner")
    public Integer getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Integer isOwner) {
        this.isOwner = isOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserHasHouseshareEntity that = (UserHasHouseshareEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (houseshareId != null ? !houseshareId.equals(that.houseshareId) : that.houseshareId != null) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (isOwner != null ? !isOwner.equals(that.isOwner) : that.isOwner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (houseshareId != null ? houseshareId.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (isOwner != null ? isOwner.hashCode() : 0);
        return result;
    }
}
