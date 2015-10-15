/**
 * 
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author SON
 *
 */
@Entity(name="notification")
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "gen", strategy = "uuid")
	@GeneratedValue(generator = "gen")
	private String id;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	private String caption;
	private String content;
	private String state;
	private Timestamp pushTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getPushTime() {
		return pushTime;
	}
	public void setPushTime(Timestamp pushTime) {
		this.pushTime = pushTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
