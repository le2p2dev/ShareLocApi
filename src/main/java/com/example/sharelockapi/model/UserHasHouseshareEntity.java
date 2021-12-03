package com.example.sharelockapi.model;

import javax.persistence.*;

@Entity
@Table(name = "user_has_houseshare", schema = "db_ShareLoc", catalog = "")
public class UserHasHouseshareEntity {
    private int userId;
    private int houseShareId;
    private Integer point;
    private Byte isOwner;

    public UserHasHouseshareEntity(int userId, int houseShareId, Integer point, Byte isOwner) {
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
    public Byte getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Byte isOwner) {
        this.isOwner = isOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserHasHouseshareEntity that = (UserHasHouseshareEntity) o;

        if (userId != that.userId) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (isOwner != null ? !isOwner.equals(that.isOwner) : that.isOwner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (isOwner != null ? isOwner.hashCode() : 0);
        return result;
    }
}
