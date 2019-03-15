package edu.uta.leanmed.pojo;

import java.io.Serializable;

/**
 * Created by Vaibhav's Console on 3/8/2019.
 */

public class UserPojo implements Serializable{
    private int userId;
    private String name;
    private String emailId;
    private String contact;
    private String password;
    private int type;
    private ZonePojo zone;
    private int status;
    private int languagePref;
    public UserPojo(){}

    public UserPojo(String name, String emailId, String contact, String password, int type) {
        this.name = name;
        this.emailId = emailId;
        this.contact = contact;
        this.password = password;
        this.type = type;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLanguagePref() {
        return languagePref;
    }

    public void setLanguagePref(int languagePref) {
        this.languagePref = languagePref;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", emailId='" + emailId + '\'' +
                ", contact='" + contact + '\'' +
                ", password='" + password + '\'' +
                ", zone=" + zone +
                ", status=" + status +
                ", languagePref=" + languagePref +
                '}';
    }
}
