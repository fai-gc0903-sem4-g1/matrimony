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
@Entity(name = "invite_add_friend")
public class InviteAddFriend implements Serializable {
    @Id
    @Column(nullable = false)
    @GenericGenerator(name="id", strategy="uuid")
    @GeneratedValue(generator="id")
    private String inviteAddFriendStackId;
    private String accountId;//foreign key account table
    private String personId;
    private Timestamp timeInviteAddFriend;

    public String getInviteAddFriendStackId() {
        return inviteAddFriendStackId;
    }

    public void setInviteAddFriendStackId(String inviteAddFriendStackId) {
        this.inviteAddFriendStackId = inviteAddFriendStackId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Timestamp getTimeInviteAddFriend() {
        return timeInviteAddFriend;
    }

    public void setTimeInviteAddFriend(Timestamp timeInviteAddFriend) {
        this.timeInviteAddFriend = timeInviteAddFriend;
    }
    
}
