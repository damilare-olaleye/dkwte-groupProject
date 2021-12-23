package com.revature.dwte.exception;

public class ReviewDoesNotExist extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReviewDoesNotExist() {
		super();
	}

	public ReviewDoesNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReviewDoesNotExist(String message, Throwable cause) {
		super(message, cause);
	}

	public ReviewDoesNotExist(String message) {
		super(message);
	}

	public ReviewDoesNotExist(Throwable cause) {
		super(cause);
	}

}
