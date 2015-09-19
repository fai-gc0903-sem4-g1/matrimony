/**
 * 
 */
package com.matrimony.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author SON
 *
 */
@Entity(name="social_network")
public class SocialNetwork {
	@Id
	@Column(nullable = false)
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id")
	private String id;
	private String userId;
	private String socialName;
	private String socialId;
	private String link;
	private Timestamp updateTime;
	private Timestamp createAt;
	
	
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSocialName() {
		return socialName;
	}
	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}
	public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
