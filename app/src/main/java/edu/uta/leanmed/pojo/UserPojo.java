package edu.uta.leanmed.pojo;

import java.io.Serializable;

/**
 * Created by Vaibhav's Console on 3/8/2019.
 */

public class UserPojo implements Serializable{
    private int userId;
    private String name;
    private int type;
    private String emailId;
    private String contact;
    private ZonePojo zone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ZonePojo getZone() {
        return zone;
    }

    public void setZone(ZonePojo zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", emailId='" + emailId + '\'' +
                ", contact='" + contact + '\'' +
                ", zone=" + zone +
                '}';
    }
}
