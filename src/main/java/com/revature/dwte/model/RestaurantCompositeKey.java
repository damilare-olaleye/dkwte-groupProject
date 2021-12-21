package com.revature.dwte.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RestaurantCompositeKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "restaurant_name", nullable = false)
	private String restaurantName;

	@Column(name = "restaurant_address", nullable = false)
	private String restaurantAddress;

	public RestaurantCompositeKey() {
		super();
	}

	public RestaurantCompositeKey(String restaurantName, String restaurantAddress) {
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
		RestaurantCompositeKey other = (RestaurantCompositeKey) obj;
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
		return "RestaurantCompositeKey [restaurantName=" + restaurantName + ", restaurantAddress=" + restaurantAddress
				+ "]";
	}

}
