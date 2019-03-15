package edu.uta.leanmed.pojo;

import java.io.Serializable;

/**
 * Created by Vaibhav's Console on 3/8/2019.
 */

public class ZonePojo implements Serializable {
    private String leanId;
    private String zoneName;
    private String zoneEmail;
    private String zoneArea;

    public ZonePojo(){
    }
    public ZonePojo(String leanId){
        this.leanId=leanId;
    }

    public ZonePojo(String leanId, String zoneName, String zoneEmail, String zoneArea) {
        this.leanId = leanId;
        this.zoneName = zoneName;
        this.zoneEmail = zoneEmail;
        this.zoneArea = zoneArea;
    }

    public String getLeanId() {
        return leanId;
    }

    public void setLeanId(String leanId) {
        this.leanId = leanId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneEmail() {
        return zoneEmail;
    }

    public void setZoneEmail(String zoneEmail) {
        this.zoneEmail = zoneEmail;
    }

    public String getZoneArea() {
        return zoneArea;
    }

    public void setZoneArea(String zoneArea) {
        this.zoneArea = zoneArea;
    }

    @Override
    public String toString() {
        return "ZonePojo{" +
                "leanId='" + leanId + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", zoneEmail='" + zoneEmail + '\'' +
                ", zoneArea='" + zoneArea + '\'' +
                '}';
    }
}
