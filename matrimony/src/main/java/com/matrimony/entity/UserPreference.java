/**
 * 
 */
package com.matrimony.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


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
	private String userId;
	private String gender;
	private String ageGap;
	private String hometown;
	private String countryside;
	private String religion;
	private String maritalStatus;
	private String weightGap;
	private String heightGap;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
