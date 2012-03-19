/**
 * 
 */
package org.language.variable;

/**
 * @author Brad
 *
 */
public class BooleanVar {
	
	/**
	 * The variable name
	 */
	private final String name;
	/**
	 * The variable value
	 */
	private boolean value;
	/**
	 * Constructs a boolean variable
	 * 
	 * @param name The variable name
	 * @param value The variable value
	 */
	protected BooleanVar(final String name, final boolean value) {
		this.name = name;
		this.value = value;
	}
	/**
	 * Gets the variable's name
	 * 
	 * @return The variable's name
	 */
	public String getName(){
		return name;
	}
	/**
	 * Gets the varaible's value
	 * 
	 * @return The variable's value
	 */
	public boolean getValue(){
		return value;
	}
	public void setValue(final boolean value){
		this.value = value;
	}
}