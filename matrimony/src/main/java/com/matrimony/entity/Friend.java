/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;

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
	@GenericGenerator(name = "gen", strategy = "uuid")
	@GeneratedValue(generator = "gen")
	private String friendId;
	@ManyToOne(optional=false)
	@JoinColumn(name = "userFrom", nullable=false, updatable=false, insertable=false)
	private User userFrom;
	@ManyToOne(optional=false)
	@JoinColumn(name = "userFromId", nullable=false, updatable=false, insertable=false)
	private User userTo;
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

	

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
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

	

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}



	public static class RequestComparator implements Comparator<Friend> {

		@Override
		public int compare(Friend f1, Friend f2) {
			if (f1.getTimeInvited().before(f2.getTimeInvited())) {
				return 1;
			} else {
				if (f1.getTimeInvited().after(f2.getTimeInvited())) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

}
