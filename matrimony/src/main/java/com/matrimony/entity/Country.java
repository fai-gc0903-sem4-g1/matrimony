/**
 * 
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author SON
 *
 */
@Entity(name="country")
public class Country implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String countryCode;
	private String name;
	private String currentCode;
	private String countryCodeISO;
	private String languageCode;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="country")
	@Cascade(value=CascadeType.ALL)
	private Set<City> cities;
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentCode() {
		return currentCode;
	}
	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}
	public String getCountryCodeISO() {
		return countryCodeISO;
	}
	public void setCountryCodeISO(String countryCodeISO) {
		this.countryCodeISO = countryCodeISO;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public Set<City> getCities() {
		return cities;
	}
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", name=" + name + ", currentCode=" + currentCode
				+ ", countryCodeISO=" + countryCodeISO + ", languageCode=" + languageCode + ", cities=" + cities + "]";
	}
	
	
	
	
	
}
