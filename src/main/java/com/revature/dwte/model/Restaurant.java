package com.revature.dwte.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Restaurant {

	// embeddedId is an alternative to the Id but for composit key
	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private RestaurantId restaurantId;

	public Restaurant() {
		super();
	}

	public Restaurant(RestaurantId restaurantId) {
		super();
		this.restaurantId = restaurantId;
	}

	public RestaurantId getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(RestaurantId restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((restaurantId == null) ? 0 : restaurantId.hashCode());
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
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + "]";
	}

}
