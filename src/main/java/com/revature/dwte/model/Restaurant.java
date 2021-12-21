package com.revature.dwte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resturantId;

	@Column(name = "restaurant_name", nullable = false)
	private String resturantName;

	@Column(name = "address", nullable = false)
	private String address;

	public Restaurant() {
		super();
	}

	public Restaurant(String resturantName, String address) {
		super();
		this.resturantName = resturantName;
		this.address = address;
	}

	public int getResturantId() {
		return resturantId;
	}

	public void setResturantId(int resturantId) {
		this.resturantId = resturantId;
	}

	public String getResturantName() {
		return resturantName;
	}

	public void setResturantName(String resturantName) {
		this.resturantName = resturantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + resturantId;
		result = prime * result + ((resturantName == null) ? 0 : resturantName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (resturantId != other.resturantId)
			return false;
		if (resturantName == null) {
			if (other.resturantName != null)
				return false;
		} else if (!resturantName.equals(other.resturantName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Restaurant [resturantId=" + resturantId + ", resturantName=" + resturantName + ", address=" + address
				+ "]";
	}

}
