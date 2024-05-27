package com.managementsystem.poc.presidio_test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LikeCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer propertyId;
    private Integer userId;
    private int likeCount;

    public LikeCount(int id, int likeCount, Integer propertyId, Integer userId) {
        this.id = id;
        this.likeCount = likeCount;
        this.propertyId = propertyId;
        this.userId = userId;
    }

    public LikeCount() {

    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
