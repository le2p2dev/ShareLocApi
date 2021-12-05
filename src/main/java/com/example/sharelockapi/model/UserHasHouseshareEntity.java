package com.example.sharelockapi.model;

import javax.persistence.*;

@Entity
@Table(name = "user_has_houseshare", schema = "db_ShareLoc")
public class UserHasHouseshareEntity {
    private int userId;
    private int houseShareId;
    private Integer point;
    private int isOwner;

    public UserHasHouseshareEntity(int userId, int houseShareId, Integer point, int isOwner) {
        this.userId = userId;
        this.houseShareId = houseShareId;
        this.point = point;
        this.isOwner = isOwner;
    }

    public UserHasHouseshareEntity() {

    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "houseshare_id")
    public int getHouseShareId() {
        return houseShareId;
    }
    public void setHouseShareId(int houseShareId) {
        this.houseShareId = houseShareId;
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
    public int getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(int isOwner) {
        this.isOwner = isOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserHasHouseshareEntity that = (UserHasHouseshareEntity) o;

        if (userId != that.userId) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (isOwner > 0 ? !(isOwner == that.isOwner) : that.isOwner > 0) return false;

        return true;
    }


}
