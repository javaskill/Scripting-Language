/**
 * 
 */
package org.language.variable.exceptions;

/**
 * @author Brad
 *
 */
public class VariableSyntaxException extends Exception {

	/**
	 * Throws an exception with no message
	 */
	public VariableSyntaxException() {
		super();
	}
	/**
	 * @param message The error message
	 */
	public VariableSyntaxException(String message) {
		super(message);
	}
	/**
	 * @param cause
	 */
	public VariableSyntaxException(Throwable cause) {
		super(cause);
	}
	/**
	 * @param message The error message
	 * @param cause
	 */
	public VariableSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

}
