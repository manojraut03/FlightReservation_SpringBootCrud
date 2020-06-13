package com.ips.flightreservation.entities;

import javax.persistence.Entity;

@Entity
public class Passenger extends AbstractEntity {


	private String firstname;
	private String lastname;
	private String middlename;
	private String email;
	private String phone;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Passenger [firstname=" + firstname + ", lastname=" + lastname + ", middlename=" + middlename
				+ ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}
