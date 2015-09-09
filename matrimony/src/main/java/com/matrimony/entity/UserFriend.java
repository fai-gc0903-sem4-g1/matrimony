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
@Entity(name="user_friend")
public class UserFriend implements Serializable {
    @Id
    @Column(nullable = false)
    @GenericGenerator(name="id", strategy="uuid")
    @GeneratedValue(generator="id")
    private String userFriendId;
    private String accountId; //foreign key account table
    private String friendId;//foreign key account table
    private Timestamp timeAddFriend;

    public String getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(String userFriendId) {
        this.userFriendId = userFriendId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public Timestamp getTimeAddFriend() {
        return timeAddFriend;
    }

    public void setTimeAddFriend(Timestamp timeAddFriend) {
        this.timeAddFriend = timeAddFriend;
    }
    
    
}
