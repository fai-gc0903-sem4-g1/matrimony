/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

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
	@GenericGenerator(name = "gen", strategy = "uuid")
	@GeneratedValue(generator = "gen")
	private String id;
	private int weight;
	private int height;
	private String firstName;
	private String lastName;
	private String middleName;
	private String username;
	private String email;
	private String password;
	private String salt;
	private String contactNumber;
	private String gender;
	private String regMethod;
	private String religion;
	private String roleName;
	private String socialNetwork;
	private String hometown;
	private String countryside;
	private String introduction;
	private String locale;
	private String maritalStatus;
	private String avatarPhoto;
	private String name;
	private String activeKey;
	private String ipLogin;
	private String registrationIP;
	@Column(length = 1000)
	private String introduce;
	private Date birthday;
	private Timestamp expiries;
	private Timestamp changedPasswordTime;
	private Timestamp loginTime;
	private Timestamp updateTime;
	private Timestamp verifiedTime;
	private Timestamp createAt;
	private boolean verified;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userInvite")
	@Cascade(value = CascadeType.ALL)
	private Set<Friend> requestInvited;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userBeInvite")
	@Cascade(value = CascadeType.ALL)
	private Set<Friend> requestAskedInvited;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@Cascade(value = CascadeType.ALL)
	private Set<Transaction> transactions;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@Cascade(value = CascadeType.ALL)
	private Set<Notification> notifications;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
	@Cascade(value = CascadeType.ALL)
	private UserPreference userPreference;

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public UserPreference getUserPreference() {
		return userPreference;
	}

	public void setUserPreference(UserPreference userPreference) {
		this.userPreference = userPreference;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegMethod() {
		return regMethod;
	}

	public void setRegMethod(String regMethod) {
		this.regMethod = regMethod;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSocialNetwork() {
		return socialNetwork;
	}

	public void setSocialNetwork(String socialNetwork) {
		this.socialNetwork = socialNetwork;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAvatarPhoto() {
		return avatarPhoto;
	}

	public void setAvatarPhoto(String avatarPhoto) {
		this.avatarPhoto = avatarPhoto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getActiveKey() {
		return activeKey;
	}

	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}

	public String getIpLogin() {
		return ipLogin;
	}

	public void setIpLogin(String ipLogin) {
		this.ipLogin = ipLogin;
	}

	public String getRegistrationIP() {
		return registrationIP;
	}

	public void setRegistrationIP(String registrationIP) {
		this.registrationIP = registrationIP;
	}

	public Timestamp getExpiries() {
		return expiries;
	}

	public void setExpiries(Timestamp expiries) {
		this.expiries = expiries;
	}

	public Timestamp getChangedPasswordTime() {
		return changedPasswordTime;
	}

	public void setChangedPasswordTime(Timestamp changedPasswordTime) {
		this.changedPasswordTime = changedPasswordTime;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getVerifiedTime() {
		return verifiedTime;
	}

	public void setVerifiedTime(Timestamp verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public Set<Friend> getRequestInvited() {
		return requestInvited;
	}

	public void setRequestInvited(Set<Friend> requestInvited) {
		this.requestInvited = requestInvited;
	}

	public Set<Friend> getRequestAskedInvited() {
		return requestAskedInvited;
	}

	public void setRequestAskedInvited(Set<Friend> requestAskedInvited) {
		this.requestAskedInvited = requestAskedInvited;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}


}
