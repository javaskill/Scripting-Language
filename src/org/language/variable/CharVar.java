package org.language.variable;

public class CharVar {
	
	private final String name;
	
	private char value;
	
	/**
	 * Constructs a Character variable
	 * 
	 * @param name The variable name
	 * @param value The variable value
	 */
	protected CharVar(final String name, final char value){
		this.name = name;
		this.value = value;
	}
	/**
	 * Gets the variable's name
	 * 
	 * @return The variable name
	 */
	public String getName(){
		return name;
	}
	/**
	 * Gets the variable's value
	 * 
	 * @return The Variable's value
	 */
	public char getValue(){
		return value;
	}
	/**
	 * Redefines the Character value
	 * 
	 * @param value The new character value
	 */
	public void setValue(final char value){
		this.value = value;
	}
	/**
	 * 
	 * @return The integer value
	 */
	public int getIntValue(){
		return (int)getValue();
	}
}