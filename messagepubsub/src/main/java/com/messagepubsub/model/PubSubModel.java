package com.messagepubsub.model;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pubdemo")
public class PubSubModel {
	@Id
	private Integer Id;
	private String Name;
	private String Address;
	
	public PubSubModel(Integer id, String name, String address) {
		super();
		Id = id;
		Name = name;
		Address = address;
	}

	public PubSubModel() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
