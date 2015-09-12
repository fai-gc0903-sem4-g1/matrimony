/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author SON
 */
@Entity(name = "user")
public class User implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false)
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id")
	private String userId;
	private int weight;
	private int height;
	private String username;
	private boolean verified;
	private String activeKey;
	private String caste;
	private boolean changedUsername;
	private String contactNumber;
	@NotEmpty
	@Email
	private String email;
	@Column(length = 1000)
	private String favoriteBook;
	@Column(length = 1000)
	private String favoriteFitness;
	@Column(length = 1000)
	private String favoriteMovie;
	@Column(length = 1000)
	private String favoriteMusic;
	@Column(length = 1000)
	private String favoriteTVshow;
	@Length(min = 2, max = 30)
	private String firstName;
	@NotEmpty
	private String gender;
	private String regMethod;
	private String religion;
	private String roleName;
	private String salt;
	private String socialNetwork;
	private String hometown;
	private String introduction;
	private String lastIPLogin;
	private String locale;
	private String location;
	private String maritalStatus;
	private String middleName;
	private String name;
	@Length(min = 4, max = 100)
	private String password;
	private String registrationIP;
	@Length(min = 2, max = 30)
	private String lastName;
	private Date birthday;
	private Timestamp lastTimeChangePassword;
	private Timestamp lastTimeLogin;
	private Timestamp lastUpdateHobby;
	private Timestamp lastUpdateProfile;
	private Timestamp registrationTime;
	private Timestamp verifiedTime;
	
	@OneToMany(mappedBy = "userFromId")
	private Set<Friend> friendFromId = new HashSet<>(0);
	@OneToMany(mappedBy = "userToId")
	private Set<Friend> friendToId = new HashSet<>(0);

	public String getActiveKey() {
		return activeKey;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getCaste() {
		return caste;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getFavoriteBook() {
		return favoriteBook;
	}

	public String getFavoriteFitness() {
		return favoriteFitness;
	}

	public String getFavoriteMovie() {
		return favoriteMovie;
	}

	public String getFavoriteMusic() {
		return favoriteMusic;
	}

	public String getFavoriteTVshow() {
		return favoriteTVshow;
	}

	public String getFirstName() {
		return firstName;
	}

	public Set<Friend> getFriendFromId() {
		return friendFromId;
	}

	public Set<Friend> getFriendToId() {
		return friendToId;
	}

	public String getGender() {
		return gender;
	}

	public int getHeight() {
		return height;
	}

	public String getHometown() {
		return hometown;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getLastIPLogin() {
		return lastIPLogin;
	}

	public String getLastName() {
		return lastName;
	}

	public Timestamp getLastTimeChangePassword() {
		return lastTimeChangePassword;
	}

	public Timestamp getLastTimeLogin() {
		return lastTimeLogin;
	}

	public Timestamp getLastUpdateHobby() {
		return lastUpdateHobby;
	}

	public Timestamp getLastUpdateProfile() {
		return lastUpdateProfile;
	}

	public String getLocale() {
		return locale;
	}

	public String getLocation() {
		return location;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRegistrationIP() {
		return registrationIP;
	}

	public Timestamp getRegistrationTime() {
		return registrationTime;
	}

	public String getRegMethod() {
		return regMethod;
	}

	public String getReligion() {
		return religion;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getSalt() {
		return salt;
	}

	public String getSocialNetwork() {
		return socialNetwork;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public Timestamp getVerifiedTime() {
		return verifiedTime;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isChangedUsername() {
		return changedUsername;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public void setChangedUsername(boolean changedUsername) {
		this.changedUsername = changedUsername;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFavoriteBook(String favoriteBook) {
		this.favoriteBook = favoriteBook;
	}

	public void setFavoriteFitness(String favoriteFitness) {
		this.favoriteFitness = favoriteFitness;
	}

	public void setFavoriteMovie(String favoriteMovie) {
		this.favoriteMovie = favoriteMovie;
	}

	public void setFavoriteMusic(String favoriteMusic) {
		this.favoriteMusic = favoriteMusic;
	}

	public void setFavoriteTVshow(String favoriteTVshow) {
		this.favoriteTVshow = favoriteTVshow;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setFriendFromId(Set<Friend> friendFromId) {
		this.friendFromId = friendFromId;
	}

	public void setFriendToId(Set<Friend> friendToId) {
		this.friendToId = friendToId;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setLastIPLogin(String lastIPLogin) {
		this.lastIPLogin = lastIPLogin;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLastTimeChangePassword(Timestamp lastTimeChangePassword) {
		this.lastTimeChangePassword = lastTimeChangePassword;
	}

	public void setLastTimeLogin(Timestamp lastTimeLogin) {
		this.lastTimeLogin = lastTimeLogin;
	}

	public void setLastUpdateHobby(Timestamp lastUpdateHobby) {
		this.lastUpdateHobby = lastUpdateHobby;
	}

	public void setLastUpdateProfile(Timestamp lastUpdateProfile) {
		this.lastUpdateProfile = lastUpdateProfile;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegistrationIP(String registrationIP) {
		this.registrationIP = registrationIP;
	}

	public void setRegistrationTime(Timestamp registrationTime) {
		this.registrationTime = registrationTime;
	}

	public void setRegMethod(String regMethod) {
		this.regMethod = regMethod;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setSocialNetwork(String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public void setVerifiedTime(Timestamp verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
