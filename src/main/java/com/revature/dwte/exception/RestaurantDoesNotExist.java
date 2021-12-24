package com.revature.dwte.exception;

public class RestaurantDoesNotExist extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2899810307122753923L;

	public RestaurantDoesNotExist() {
		super();
	}

	public RestaurantDoesNotExist(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RestaurantDoesNotExist(String message, Throwable cause) {
		super(message, cause);
	}

	public RestaurantDoesNotExist(String message) {
		super(message);
	}

	public RestaurantDoesNotExist(Throwable cause) {
		super(cause);
	}

}
