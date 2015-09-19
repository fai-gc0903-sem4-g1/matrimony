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
@Entity
public class TransactionHistory {
	@Id
	@Column(nullable = false)
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id")
	private String id;
	private String userId;
	private String method;
	private double amount;
	private Timestamp time;
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
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
}
