package com.demo.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "patient")
public class PatientModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	private String city;
	
	private String zip;
	
	@Transient
	private String lname; 

	/*
	 * public PatientModel() { super();
	 * 
	 * }
	 * 
	 * public PatientModel(long id, String name, String city, String zip) { super();
	 * this.id = id; this.name = name; this.city = city; this.zip = zip;
	 * this.lname=name+zip; }
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "PatientModel [id=" + id + ", name=" + name + ", city=" + city + ",lname=" + lname + ", zip=" + zip + "]";
	}

	
	public String getLname(String lname) {
		return lname=name+lname;
	}
	
	
	public void setLname(String lname) {
		this.lname = lname;
	}
}
