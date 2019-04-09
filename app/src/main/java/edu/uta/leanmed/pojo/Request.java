package edu.uta.leanmed.pojo;

import java.io.Serializable;

/**
 * Created by Vaibhav's Console on 3/23/2019.
 */

public class Request implements Serializable {
    private int requestId;
    private Medicine medicine;
    private int quantity;
    private Zone zone;
    private String status;
    private String comments;
    private int orderId;
    private String createdDate;
    private String modifiedDate;
    private User createdUser;
    private User acceptedUser;
}
