package edu.uta.leanmed.pojo;

import java.io.Serializable;

/**
 * Created by Vaibhav's Console on 3/23/2019.
 */

public class Inventory implements Serializable {
    private int inventoryId;
    private Medicine medicine;
    private int units;
    private String expiryDate;
    private String donationDate;
    private String idBox;
    private Donor donor;

    public Inventory() {
    }

    public Inventory(int inventoryId, Medicine medicine, int units, String expiryDate, String donationDate, String idBox, Donor donor) {
        this.inventoryId = inventoryId;
        this.medicine = medicine;
        this.units = units;
        this.expiryDate = expiryDate;
        this.donationDate = donationDate;
        this.idBox = idBox;
        this.donor = donor;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getIdBox() {
        return idBox;
    }

    public void setIdBox(String idBox) {
        this.idBox = idBox;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }
}
