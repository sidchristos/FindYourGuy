package com.example.threedots.findyourguy.Model;

/**
 * Created by Stef on 06-May-17.
 */

public class User {

    private long UserId;
    private double AvgRating;
    private long CRating;
    private String Name;
    private String Address;
    private String Attitude;
    private String Description;

    public User(){}

    public User(long userId, double avgRating, long cRating, String name, String address, String attitude, String description)
    {

        UserId = userId;
        AvgRating = avgRating;
        CRating = cRating;
        Name = name;
        Address = address;
        Attitude = attitude;
        Description = description;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public double getAvgRating() {
        return AvgRating;
    }

    public void setAvgRating(double avgRating) {
        AvgRating = avgRating;
    }

    public long getCRating() {
        return CRating;
    }

    public void setCRating(long CRating) {
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
