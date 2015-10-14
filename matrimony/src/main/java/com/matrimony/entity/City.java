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

import com.matrimony.database.Mark;

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
	@JoinColumn(name="countryCode")
	private Country country;
	@OneToOne(mappedBy="city")
	@Cascade(value=CascadeType.ALL)
	private Mark mark;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Mark getMark() {
		return mark;
	}
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
}
