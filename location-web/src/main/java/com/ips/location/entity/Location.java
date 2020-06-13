package com.ips.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="locationtable")
public class Location {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String code;
	@Column
	private String name;
	@Column
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", code=" + code + ", name=" + name + ", type=" + type + "]";
	}
	
	
}
