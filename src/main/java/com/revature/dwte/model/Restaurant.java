package com.revature.dwte.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
public class Restaurant {

	// embeddedId for composite key
	@EmbeddedId
	private RestaurantCompositeKey restaurantCompositeKey;

	@Generated(GenerationTime.INSERT)
	@Column(columnDefinition = "serial", insertable = false)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int restaurantId;

	public Restaurant() {
		super();
	}

	public Restaurant(int restaurantId) {
		super();
		this.restaurantId = restaurantId;
	}

	public Restaurant(RestaurantCompositeKey restaurantCompositeKey, int restaurantId) {
		super();
		this.restaurantCompositeKey = restaurantCompositeKey;
		this.restaurantId = restaurantId;
	}

	public RestaurantCompositeKey getRestaurantCompositeKey() {
		return restaurantCompositeKey;
	}

	public void setRestaurantCompositeKey(RestaurantCompositeKey restaurantCompositeKey) {
		this.restaurantCompositeKey = restaurantCompositeKey;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((restaurantCompositeKey == null) ? 0 : restaurantCompositeKey.hashCode());
		result = prime * result + restaurantId;
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
		if (restaurantCompositeKey == null) {
			if (other.restaurantCompositeKey != null)
				return false;
		} else if (!restaurantCompositeKey.equals(other.restaurantCompositeKey))
			return false;
		if (restaurantId != other.restaurantId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantCompositeKey=" + restaurantCompositeKey + ", restaurantId=" + restaurantId + "]";
	}

}
