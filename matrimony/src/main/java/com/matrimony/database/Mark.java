/**
 * 
 */
package com.matrimony.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.matrimony.entity.City;

/**
 * @author SON
 *
 */
@Entity(name="mark")
public class Mark {
	@Id
//	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name = "property", value="city") })
//	@GeneratedValue(generator="gen")
	private long id;
	private String choi;
	@OneToOne
	@PrimaryKeyJoinColumn
	private City city;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getChoi() {
		return choi;
	}
	public void setChoi(String choi) {
		this.choi = choi;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}
