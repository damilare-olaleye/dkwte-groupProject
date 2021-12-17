package com.revature.dwte.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resturantId;

	private String resturantName;

	public Restaurant() {
		super();
	}

	public Restaurant(int resturantId, String resturantName) {
		super();
		this.resturantId = resturantId;
		this.resturantName = resturantName;
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

	@Override
	public int hashCode() {
		return Objects.hash(resturantId, resturantName);
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
		return resturantId == other.resturantId && Objects.equals(resturantName, other.resturantName);
	}

	@Override
	public String toString() {
		return "Restaurant [resturantId=" + resturantId + ", resturantName=" + resturantName + "]";
	}

}
