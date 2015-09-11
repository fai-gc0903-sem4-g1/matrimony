/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author SON
 */
@Entity(name = "tableFriend")
public class TableFriends implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)
    @GenericGenerator(name = "id", strategy = "uuid")
    @GeneratedValue(generator = "id")
    private String friendId;
    @Column(name = "friend_formId")
    private String friendFromId;
    @Column(name = "friend_toId")
    private String friendToId;
    @Column(name = "status")
    private int status = 1 ;
    @OneToMany(mappedBy = "table")
    private Set<User> list = new HashSet<User>();
    private Timestamp timeInvited;
    private Timestamp timeDenied;
    private Timestamp timeAccepted;

    public Set<User> getList() {
        return list;
    }

    public void setList(Set<User> list) {
        this.list = list;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendFromId() {
        return friendFromId;
    }

    public void setFriendFromId(String friendFromId) {
        this.friendFromId = friendFromId;
    }

    public String getFriendToId() {
        return friendToId;
    }

    public void setFriendToId(String friendToId) {
        this.friendToId = friendToId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
