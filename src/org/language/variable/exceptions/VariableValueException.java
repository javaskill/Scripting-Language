/**
 * 
 */
package org.language.variable.exceptions;

/**
 * @author Brad
 * 
 * Thrown to indicate the value of a variable is not applicable to the variable type
 *
 */
public class VariableValueException extends Exception {

	/**
	 * A general Variable Value Exception with no error message
	 */
	public VariableValueException() {
		super();
	}
	/**
	 * @param message The error message
	 */
	public VariableValueException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public VariableValueException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message The error message
	 * @param cause
	 */
	public VariableValueException(String message, Throwable cause) {
		super(message, cause);
	}

}
