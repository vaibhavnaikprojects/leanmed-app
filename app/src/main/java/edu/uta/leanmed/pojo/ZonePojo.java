package edu.uta.leanmed.pojo;

import java.io.Serializable;

/**
 * Created by Vaibhav's Console on 3/8/2019.
 */

public class ZonePojo implements Serializable {
    private int zoneId;
    private String zoneName;

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString() {
        return "ZonePojo{" +
                "zoneId=" + zoneId +
                ", zoneName='" + zoneName + '\'' +
                '}';
    }
}
