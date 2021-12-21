package com.revature.dwte.exception;

public class InvalidLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidLoginException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidLoginException(String arg0) {
		super(arg0);
	}

	public InvalidLoginException(Throwable arg0) {
		super(arg0);
	}

}
