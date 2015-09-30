/**
 * 
 */
package com.facebook.entity;

/**
 * @author SON
 *
 */
public class UserProfile {
	private String name;

	private String first_name;

	private String last_name;

	private AgeRange age_range;

	private String email;

	private String locale;

	private String link;

	private String gender;

	private String id;

	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public AgeRange getAgeRange() {
		return this.age_range;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.first_name;
	}

	public String getGender() {
		return this.gender;
	}

	public String getId() {
		return this.id;
	}

	public String getLastName() {
		return this.last_name;
	}

	public String getLink() {
		return this.link;
	}

	public String getLocale() {
		return this.locale;
	}

	public String getName() {
		return this.name;
	}

	public void setAgeRange(AgeRange age_range) {
		this.age_range = age_range;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setName(String name) {
		this.name = name;
	}
}
