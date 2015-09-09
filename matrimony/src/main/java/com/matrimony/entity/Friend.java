/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author SON
 */
@Entity(name="friend")
public class Friend implements Serializable {
    @Id
    @Column(nullable = false)
    @GenericGenerator(name = "id", strategy = "uuid")
    @GeneratedValue(generator = "id")
    private String friendId;
    private String senderId;
    private String receiverId;
    private String status;
    private Timestamp timeInvited;
    private Timestamp timeDenied;
    private Timestamp timeAccepted;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimeInvited() {
        return timeInvited;
    }

    public void setTimeInvited(Timestamp timeInvited) {
        this.timeInvited = timeInvited;
    }

    public Timestamp getTimeDenied() {
        return timeDenied;
    }

    public void setTimeDenied(Timestamp timeDenied) {
        this.timeDenied = timeDenied;
    }

    public Timestamp getTimeAccepted() {
        return timeAccepted;
    }

    public void setTimeAccepted(Timestamp timeAccepted) {
        this.timeAccepted = timeAccepted;
    }
    
    
}
