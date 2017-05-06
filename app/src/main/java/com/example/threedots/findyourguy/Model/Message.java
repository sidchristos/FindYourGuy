package com.example.threedots.findyourguy.Model;

/**
 * Created by Stef on 06-May-17.
 */

public class Message {
    private long ID;
    private long UIDSender;
    private String Message,UserName;

    public Message(){}

    public Message(long ID, String UserName, long  UIDSender, String Message){
        this.ID = ID;
        this.UIDSender = UIDSender;
        this.Message = Message;
        this.UserName=UserName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setUIDSender(long UIDSender) {
        this.UIDSender = UIDSender;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public long getID() {
        return ID;
    }

    public long getUIDSender() {
        return UIDSender;
    }

    public String getMessage() {
        return Message;
    }
}
