package com.QoreMigration.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.QoreMigration.util.StringJsonUserType;


@Entity
@org.hibernate.annotations.TypeDef(name = "StringJsonUserType", typeClass = StringJsonUserType.class)
public class ObjectModel {

	@Id
	 @org.hibernate.annotations.Type(type = "StringJsonUserType")
		private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	
}
