package com.example.threedots.findyourguy.Model;

/**
 * Created by Stef on 06-May-17.
 */

public class Room {
    private long ID;
    private String Title;
    private long UIDCreator;
    private boolean IsPrivate;

    public Room(){}

    public Room(long ID, String Title, long UIDCreator, boolean IsPrivate){
        this.ID = ID;
        this.Title = Title;
        this.UIDCreator = UIDCreator;
        this.IsPrivate = IsPrivate;
    }

    public long getID() {
        return ID;
    }

    public long getUIDCreator() {
        return UIDCreator;
    }

    public String getTitle() {
        return Title;
    }

    public boolean getIsPrivate(){
        return IsPrivate;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setUIDCreator(long UIDCreator) {
        this.UIDCreator = UIDCreator;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setIsPrivate(boolean IsPrivate){
        this.IsPrivate = IsPrivate;
    }
}
