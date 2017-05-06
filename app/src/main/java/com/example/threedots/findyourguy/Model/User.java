package com.example.threedots.findyourguy.Model;

/**
 * Created by Stef on 06-May-17.
 */

public class User {
    private Long UserId;
    private Long AvgRating;
    private Long CRating;
    private String Name;
    private String Address;
    private String Attitude;
    private String Description;

    public User(){}
    public User(Long userId, Long avgRating, Long cRating, String name, String address, String attritube, String description)
    {

        UserId = userId;
        AvgRating = avgRating;
        CRating = cRating;
        Name = name;
        Address = address;
        Attitude = attritube;
        Description = description;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getAvgRating() {
        return AvgRating;
    }

    public void setAvgRating(Long avgRating) {
        AvgRating = avgRating;
    }

    public Long getCRating() {
        return CRating;
    }

    public void setCRating(Long CRating) {
        this.CRating = CRating;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAttitude() {
        return Attitude;
    }

    public void setAttitude(String attitude) {
        Attitude = attitude;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
