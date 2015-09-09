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

    @Id
    @Column(nullable = false)
    @GenericGenerator(name = "id", strategy = "uuid")
    @GeneratedValue(generator = "id")
    private String userId;
    @Length(min = 4, max = 100)
    private String password;
    private String salt;
    @NotEmpty
    @Email
    private String email;
    @Length(min = 2, max = 30)
    private String firstName;
    private String middleName;
    @Length(min = 2, max = 30)
    private String lastName;
    @NotEmpty
    private String gender;
    private String username;
    private String name;
    private Date birthday;
    private String roleName;
    private String contactNumber;
    private String registrationIP;
    private String activeKey;
    private String lastIPLogin;
    private String regMethod;
    private boolean changedUsername;
    private Timestamp lastTimeLogin;
    private Timestamp registrationTime;
    private Timestamp lastTimeChangePassword;
    private Timestamp verifiedTime;
    private boolean verified;

    private String maritalStatus;
    private String socialNetwork;
    private int height;
    private int weight;
    private String hometown;
    private String locale;
    private String location;
    private String religion;
    private String caste;
    private String introduction;
    private Timestamp lastUpdateProfile;

    @Column(length = 1000)
    private String favoriteMusic;
    @Column(length = 1000)
    private String favoriteMovie;
    @Column(length = 1000)
    private String favoriteFitness;
    @Column(length = 1000)
    private String favoriteTVshow;
    @Column(length = 1000)
    private String favoriteBook;
    private Timestamp lastUpdateHobby;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isChangedUsername() {
        return changedUsername;
    }

    public void setChangedUsername(boolean changedUsername) {
        this.changedUsername = changedUsername;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRegistrationIP() {
        return registrationIP;
    }

    public void setRegistrationIP(String registrationIP) {
        this.registrationIP = registrationIP;
    }

    public String getActiveKey() {
        return activeKey;
    }

    public void setActiveKey(String activeKey) {
        this.activeKey = activeKey;
    }

    public String getLastIPLogin() {
        return lastIPLogin;
    }

    public void setLastIPLogin(String lastIPLogin) {
        this.lastIPLogin = lastIPLogin;
    }

    public String getRegMethod() {
        return regMethod;
    }

    public void setRegMethod(String regMethod) {
        this.regMethod = regMethod;
    }

    public Timestamp getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(Timestamp lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Timestamp getLastTimeChangePassword() {
        return lastTimeChangePassword;
    }

    public void setLastTimeChangePassword(Timestamp lastTimeChangePassword) {
        this.lastTimeChangePassword = lastTimeChangePassword;
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

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
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

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Timestamp getLastUpdateProfile() {
        return lastUpdateProfile;
    }

    public void setLastUpdateProfile(Timestamp lastUpdateProfile) {
        this.lastUpdateProfile = lastUpdateProfile;
    }

    public String getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(String favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }

    public String getFavoriteMovie() {
        return favoriteMovie;
    }

    public void setFavoriteMovie(String favoriteMovie) {
        this.favoriteMovie = favoriteMovie;
    }

    public String getFavoriteFitness() {
        return favoriteFitness;
    }

    public void setFavoriteFitness(String favoriteFitness) {
        this.favoriteFitness = favoriteFitness;
    }

    public String getFavoriteTVshow() {
        return favoriteTVshow;
    }

    public void setFavoriteTVshow(String favoriteTVshow) {
        this.favoriteTVshow = favoriteTVshow;
    }

    public String getFavoriteBook() {
        return favoriteBook;
    }

    public void setFavoriteBook(String favoriteBook) {
        this.favoriteBook = favoriteBook;
    }

    public Timestamp getLastUpdateHobby() {
        return lastUpdateHobby;
    }

    public void setLastUpdateHobby(Timestamp lastUpdateHobby) {
        this.lastUpdateHobby = lastUpdateHobby;
    }

}
