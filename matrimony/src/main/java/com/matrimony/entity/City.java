/**
 * 
 */
package com.matrimony.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author SON
 *
 */
@Entity(name="city")
public class City {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="country1", updatable=false, insertable=false)
	private Country country1;
	@ManyToOne
	@JoinColumn(name="country2", updatable=false, insertable=false)
	private Country country2;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Country getCountry1() {
		return country1;
	}
	public void setCountry1(Country country1) {
		this.country1 = country1;
	}
	public Country getCountry2() {
		return country2;
	}
	public void setCountry2(Country country2) {
		this.country2 = country2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", country1=" + country1 + ", country2=" + country2 + ", name=" + name + "]";
	}
	
	
	
}
