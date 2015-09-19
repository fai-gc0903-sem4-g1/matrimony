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
@Entity(name="favorite_music")
public class FavoriteMusic {
	@Id
	@Column(nullable = false)
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id")
	private String id;
	private String userId;
	private String name;
	private Timestamp creatAt;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreatAt() {
		return creatAt;
	}
	public void setCreatAt(Timestamp creatAt) {
		this.creatAt = creatAt;
	}
}
