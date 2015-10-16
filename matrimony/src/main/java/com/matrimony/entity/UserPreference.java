/**
 * 
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 * @author SON
 *
 */
@Entity(name="user_preference")
public class UserPreference implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private User user;
	private String gender;
	private String ageGap;
	private String hometown;
	private String countryside;
	private String religion;
	private String maritalStatus;
	private String weightGap;
	private String heightGap;
	private Timestamp updatedTime;

	
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgeGap() {
		return ageGap;
	}
	public void setAgeGap(String ageGap) {
		this.ageGap = ageGap;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getCountryside() {
		return countryside;
	}
	public void setCountryside(String countryside) {
		this.countryside = countryside;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getWeightGap() {
		return weightGap;
	}
	public void setWeightGap(String weightGap) {
		this.weightGap = weightGap;
	}
	public String getHeightGap() {
		return heightGap;
	}
	public void setHeightGap(String heightGap) {
		this.heightGap = heightGap;
	}
	
	
	
}
