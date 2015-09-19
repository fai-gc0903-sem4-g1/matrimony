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
	@Length(min = 8, max = 64, message="Mật khẩu phải từ 8 -> 64 ký tự")
	private String password;
	private String registrationIP;
	@Length(min = 2, max = 20,message="Tên phải từ 2 -> 20 ký tự")
	private String firstName;
	@Length(min = 2, max = 20, message="Họ phải từ 2 -> 20 ký tự")
	private String lastName;
	private String username;
	private String activeKey;
	private String caste;
	private String contactNumber;
	@NotEmpty(message="Email không được bỏ trống")
	@Email(message="Email sai định dạng")
	private String email;
	@NotEmpty(message="Xin chọn giới tính")
	private String gender;
	@NotEmpty
	private String regMethod;
	private String religion;
	private String roleName;
	@NotEmpty
	private String salt;
	private String socialNetwork;
	private String hometown;
	private String introduction;
	private String locale;
	private String location;
	private String maritalStatus;
	private String middleName;
	private String avatarPhoto;
	private String name;
	private Date birthday;
	private String ipLogin;
	private Timestamp changedPasswordTime;
	private Timestamp loginTime;
	private Timestamp updateTime;
	private Timestamp logoutTime;
	private Timestamp verifiedTime;
	private Timestamp expiresLicence;
	private Timestamp createAt;
	private boolean verified;

	@OneToMany(mappedBy = "userFromId")
	private Set<Friend> friendFromId;
	@OneToMany(mappedBy = "userToId")
	private Set<Friend> friendToId;
	@OneToMany(mappedBy="userId")
	private Set<TransactionHistory> transactionHistories;
	@OneToMany(mappedBy="userId")
	private Set<SocialNetwork> socialNetworks;
	
	public Set<SocialNetwork> getSocialNetworks() {
		return socialNetworks;
	}

	public void setSocialNetworks(Set<SocialNetwork> socialNetworks) {
		this.socialNetworks = socialNetworks;
	}

	public Set<TransactionHistory> getTransactionHistories() {
		return transactionHistories;
	}

	public void setTransactionHistories(Set<TransactionHistory> transactionHistories) {
		this.transactionHistories = transactionHistories;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegistrationIP() {
		return registrationIP;
	}

	public void setRegistrationIP(String registrationIP) {
		this.registrationIP = registrationIP;
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

	public String getActiveKey() {
		return activeKey;
	}

	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIpLogin() {
		return ipLogin;
	}

	public void setIpLogin(String ipLogin) {
		this.ipLogin = ipLogin;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Timestamp getVerifiedTime() {
		return verifiedTime;
	}

	public void setVerifiedTime(Timestamp verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Set<Friend> getFriendFromId() {
		return friendFromId;
	}

	public void setFriendFromId(Set<Friend> friendFromId) {
		this.friendFromId = friendFromId;
	}

	public Set<Friend> getFriendToId() {
		return friendToId;
	}

	public void setFriendToId(Set<Friend> friendToId) {
		this.friendToId = friendToId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getExpiresLicence() {
		return expiresLicence;
	}

	public void setExpiresLicence(Timestamp expiresLicence) {
		this.expiresLicence = expiresLicence;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}


}
