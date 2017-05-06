package com.example.threedots.findyourguy.Model;

/**
 * Created by Stef on 06-May-17.
 */

public class Message {
    private String ID;
    private String UIDSender;
    private String Message,UserName;

    public Message(){}

    public Message(String ID, String UserName, String  UIDSender, String Message){
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

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUIDSender(String UIDSender) {
        this.UIDSender = UIDSender;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getID() {
        return ID;
    }

    public String getUIDSender() {
        return UIDSender;
    }

    public String getMessage() {
        return Message;
    }
}
