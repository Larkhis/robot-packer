package com.xspeedit.exception;

/**
 * RobotPacker invalid input exception
 * @author lokmanearkhis
 */
public class RobotPackerInvalidInputException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5720824433916483860L;
	
	/**
	 * Contructs the RobotPackerInvalidInputException with message
	 * @param message - message of the exception
	 */
	public RobotPackerInvalidInputException(String message) {
        super(message);
    }
	
}
