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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String id;
	private int weight;
	private int height;
	private String firstName;
	private String lastName;
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
	private Date birthday;
	private String activeKey;
	private String ipLogin;
	private String registrationIP;
	private Timestamp expiries;
	private Timestamp changedPasswordTime;
	private Timestamp loginTime;
	private Timestamp updateTime;
	private Timestamp verifiedTime;
	private Timestamp createAt;
	private boolean verified;
    @OneToMany(mappedBy = "userFromId")
    @JsonIgnore
    private Set<Friend> friendFromId;
    @OneToMany(mappedBy = "userToId")
    @JsonIgnore
    private Set<Friend> friendToId;
    @OneToMany(mappedBy = "userId")
    private Set<Transaction> transactions;
    @OneToMany(mappedBy = "userId")
    private Set<SocialNetwork> socialNetworks;
    @OneToMany(mappedBy = "userId")
    private Set<FavoriteBook> favoriteBooks;
    @OneToMany(mappedBy = "userId")
    private Set<FavoriteFitness> favoriteFitnesses;
    @OneToMany(mappedBy = "userId")
    private Set<FavoriteMusic> favoriteMusics;
    @OneToMany(mappedBy = "userId")
    private Set<FavoriteTVShow> favoriteTVShows;
    @OneToMany(mappedBy = "userId")
    private Set<FavoriteMovie> favoriteMovies;

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

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(Set<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public Set<FavoriteBook> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(Set<FavoriteBook> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    public Set<FavoriteFitness> getFavoriteFitnesses() {
        return favoriteFitnesses;
    }

    public void setFavoriteFitnesses(Set<FavoriteFitness> favoriteFitnesses) {
        this.favoriteFitnesses = favoriteFitnesses;
    }

    public Set<FavoriteMusic> getFavoriteMusics() {
        return favoriteMusics;
    }

    public void setFavoriteMusics(Set<FavoriteMusic> favoriteMusics) {
        this.favoriteMusics = favoriteMusics;
    }

    public Set<FavoriteTVShow> getFavoriteTVShows() {
        return favoriteTVShows;
    }

    public void setFavoriteTVShows(Set<FavoriteTVShow> favoriteTVShows) {
        this.favoriteTVShows = favoriteTVShows;
    }

    public Set<FavoriteMovie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Set<FavoriteMovie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", weight=" + weight + ", height=" + height + ", firstName=" + firstName
                + ", lastName=" + lastName + ", username=" + username + ", email=" + email + ", password=" + password
                + ", salt=" + salt + ", contactNumber=" + contactNumber + ", gender=" + gender + ", regMethod="
                + regMethod + ", religion=" + religion + ", roleName=" + roleName + ", socialNetwork=" + socialNetwork
                + ", hometown=" + hometown + ", countryside=" + countryside + ", introduction=" + introduction
                + ", locale=" + locale + ", maritalStatus=" + maritalStatus + ", avatarPhoto=" + avatarPhoto
                + ", name=" + name + ", birthday=" + birthday + ", activeKey=" + activeKey + ", ipLogin=" + ipLogin
                + ", registrationIP=" + registrationIP + ", expiries=" + expiries + ", changedPasswordTime="
                + changedPasswordTime + ", loginTime=" + loginTime + ", updateTime=" + updateTime + ", verifiedTime="
                + verifiedTime + ", createAt=" + createAt + ", verified=" + verified + ", friendFromId=" + friendFromId
                + ", friendToId=" + friendToId + ", transactions=" + transactions + ", socialNetworks="
                + socialNetworks + ", favoriteBooks=" + favoriteBooks + ", favoriteFitnesses=" + favoriteFitnesses
                + ", favoriteMusics=" + favoriteMusics + ", favoriteTVShows=" + favoriteTVShows + ", favoriteMovies="
                + favoriteMovies + ", getId()=" + getId() + ", getWeight()=" + getWeight() + ", getHeight()="
                + getHeight() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
                + ", getUsername()=" + getUsername() + ", getEmail()=" + getEmail() + ", getPassword()="
                + getPassword() + ", getSalt()=" + getSalt() + ", getContactNumber()=" + getContactNumber()
                + ", getGender()=" + getGender() + ", getRegMethod()=" + getRegMethod() + ", getReligion()="
                + getReligion() + ", getRoleName()=" + getRoleName() + ", getSocialNetwork()=" + getSocialNetwork()
                + ", getHometown()=" + getHometown() + ", getCountryside()=" + getCountryside()
                + ", getIntroduction()=" + getIntroduction() + ", getLocale()=" + getLocale() + ", getMaritalStatus()="
                + getMaritalStatus() + ", getAvatarPhoto()=" + getAvatarPhoto() + ", getName()=" + getName()
                + ", getBirthday()=" + getBirthday() + ", getActiveKey()=" + getActiveKey() + ", getIpLogin()="
                + getIpLogin() + ", getRegistrationIP()=" + getRegistrationIP() + ", getExpiries()=" + getExpiries()
                + ", getChangedPasswordTime()=" + getChangedPasswordTime() + ", getLoginTime()=" + getLoginTime()
                + ", getUpdateTime()=" + getUpdateTime() + ", getVerifiedTime()=" + getVerifiedTime()
                + ", getCreateAt()=" + getCreateAt() + ", isVerified()=" + isVerified() + ", getFriendFromId()="
                + getFriendFromId() + ", getFriendToId()=" + getFriendToId() + ", getTransactions()="
                + getTransactions() + ", getSocialNetworks()=" + getSocialNetworks() + ", getFavoriteBooks()="
                + getFavoriteBooks() + ", getFavoriteFitnesses()=" + getFavoriteFitnesses() + ", getFavoriteMusics()="
                + getFavoriteMusics() + ", getFavoriteTVShows()=" + getFavoriteTVShows() + ", getFavoriteMovies()="
                + getFavoriteMovies() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

}
