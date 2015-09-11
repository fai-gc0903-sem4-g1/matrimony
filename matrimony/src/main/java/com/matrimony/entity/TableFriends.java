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
public class TableFriends implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String friendFromId;
    private String friendToId;

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
    
}
