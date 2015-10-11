/**
 * 
 */
package com.matrimony.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * @author SON
 *
 */
@Entity(name="user_preference")
public class UserPreference {
	@Id
	private String userId;
	private String genderLike;
	private String ageGapLike;
	private String hometownLike;
	private String countrysideLike;
	private String religionLike;
	private String maritalStatusLike;
	private String weightGapLike;
	private String heightGapLike;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGenderLike() {
		return genderLike;
	}
	public void setGenderLike(String genderLike) {
		this.genderLike = genderLike;
	}
	public String getAgeGapLike() {
		return ageGapLike;
	}
	public void setAgeGapLike(String ageGapLike) {
		this.ageGapLike = ageGapLike;
	}
	public String getHometownLike() {
		return hometownLike;
	}
	public void setHometownLike(String hometownLike) {
		this.hometownLike = hometownLike;
	}
	public String getCountrysideLike() {
		return countrysideLike;
	}
	public void setCountrysideLike(String countrysideLike) {
		this.countrysideLike = countrysideLike;
	}
	public String getReligionLike() {
		return religionLike;
	}
	public void setReligionLike(String religionLike) {
		this.religionLike = religionLike;
	}
	public String getMaritalStatusLike() {
		return maritalStatusLike;
	}
	public void setMaritalStatusLike(String maritalStatusLike) {
		this.maritalStatusLike = maritalStatusLike;
	}
	public String getWeightGapLike() {
		return weightGapLike;
	}
	public void setWeightGapLike(String weightGapLike) {
		this.weightGapLike = weightGapLike;
	}
	public String getHeightGapLike() {
		return heightGapLike;
	}
	public void setHeightGapLike(String heightGapLike) {
		this.heightGapLike = heightGapLike;
	}
	
	
	
	
	
}
