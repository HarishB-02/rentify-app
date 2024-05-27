package com.managementsystem.poc.presidio_test.model;

public class PropertyWrapper {
    private Integer propertyId;
    private String propertyName;
    private String propertyRent;
    private String propertyType;
    private String selleremail;
    private String address;
    private String place;
    private Integer bedrooms;
    private Integer bathrooms;
    private String nearbyLandmark;
    private int likes;
    private String sqFt;
    private int sellerId;

    public PropertyWrapper() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getNearbyLandmark() {
        return nearbyLandmark;
    }

    public void setNearbyLandmark(String nearbyLandmark) {
        this.nearbyLandmark = nearbyLandmark;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getSelleremail() {
        return selleremail;
    }

    public void setSelleremail(String selleremail) {
        this.selleremail = selleremail;
    }

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyRent() {
        return propertyRent;
    }

    public void setPropertyRent(String propertyRent) {
        this.propertyRent = propertyRent;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getSqFt() {
        return sqFt;
    }

    public void setSqFt(String sqFt) {
        this.sqFt = sqFt;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
