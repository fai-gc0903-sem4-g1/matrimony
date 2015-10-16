/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@GenericGenerator(name = "gen", strategy = "uuid")
	@GeneratedValue(generator = "gen")
	private String id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "userInviteId",nullable=false, updatable=false)
	private User userInvite;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "userBeInviteId",nullable=false, updatable=false)
	private User userBeInvite;
	private int status;
	private String state;
	private Timestamp timeInvited;
	private Timestamp timeDenied;
	private Timestamp timeAccepted;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUserInvite() {
		return userInvite;
	}

	public void setUserInvite(User userFrom) {
		this.userInvite = userFrom;
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

	

	public User getUserBeInvite() {
		return userBeInvite;
	}

	public void setUserBeInvite(User userTo) {
		this.userBeInvite = userTo;
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
