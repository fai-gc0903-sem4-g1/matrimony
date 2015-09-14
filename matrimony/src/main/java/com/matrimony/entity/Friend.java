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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author SON
 */
@Entity(name = "friend")
public class Friend implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false)
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id")
	private String friendId;
	private User userFromId;
	private User userToId;
	private int status;
	private Timestamp timeInvited;
	private Timestamp timeDenied;
	private Timestamp timeAccepted;

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}



	public User getUserFromId() {
		return userFromId;
	}

	public void setUserFromId(User userFromId) {
		this.userFromId = userFromId;
	}

	public User getUserToId() {
		return userToId;
	}

	public void setUserToId(User userToId) {
		this.userToId = userToId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
