package org.language.variable;

public class StringVar {
	
	/**
	 * The variable name
	 */
	private String name;
	
	/**
	 * The variable value
	 */
	private String value;
	
	/**
	 * Constructs a general String variable
	 * 
	 * @param name The Strings name
	 * @param value The String's value
	 */
	protected StringVar(final String name, final String value){
		System.out.println(value);
		this.name = name;
		this.value = value;
	}
	/**
	 * 
	 * @param toadd The String to add to the current value
	 */
	public void plusEquals(final String toadd){
		value += toadd;
	}
	/**
	 * Changes the value to the new specified value
	 * 
	 * @param value The new value
	 */
	public void setValue(final String value){
		this.value = value;
	}
	/**
	 * 
	 * @return The value of the String
	 */
	public String getValue(){
		return value;
	}
	/**
	 * 
	 * @return The variables name
	 */
	public String getName(){
		return name;
	}
}