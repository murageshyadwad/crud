package com.messagepubsub.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pubdemo1")
public class PubSubModel1 {
	@Id
	private Integer patientid;
	private String patientname;
	private String city;
	private Integer zip;
	
	
	public Integer getPatientid() {
		return patientid;
	}
	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public PubSubModel1(Integer patientid, String patientname, String city, int zip) {
		super();
		this.patientid = patientid;
		this.patientname = patientname;
		this.city=city;
		this.zip = zip;
	}
	public PubSubModel1() {
		super();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
