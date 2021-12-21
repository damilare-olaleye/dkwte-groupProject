package com.revature.dwte.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RestaurantId implements Serializable {

	@Column(name = "restaurant_name", nullable = false)
	private String restaurantName;

	@Column(name = "address", nullable = false)
	private String restaurantAddress;

	public RestaurantId() {
		super();
	}

	public RestaurantId(String restaurantName, String restaurantAddress) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((restaurantAddress == null) ? 0 : restaurantAddress.hashCode());
		result = prime * result + ((restaurantName == null) ? 0 : restaurantName.hashCode());
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
		RestaurantId other = (RestaurantId) obj;
		if (restaurantAddress == null) {
			if (other.restaurantAddress != null)
				return false;
		} else if (!restaurantAddress.equals(other.restaurantAddress))
			return false;
		if (restaurantName == null) {
			if (other.restaurantName != null)
				return false;
		} else if (!restaurantName.equals(other.restaurantName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestaurantId [restaurantName=" + restaurantName + ", restaurantAddress=" + restaurantAddress + "]";
	}

}
