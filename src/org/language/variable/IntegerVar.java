/**
 * 
 */
package org.language.variable;

/**
 * @author Brad
 *
 */
public class IntegerVar {
	
	/**
	 * The variables name
	 */
	private final String name;
	/**
	 * The variables value
	 */
	private int value;
	/**
	 * 
	 * @param name The name to identify the variable
	 * @param value The modifiable value of the variable
	 */
	protected IntegerVar(final String name, int value) {
		this.name = name;
		this.value = value;
	}
	/**
	 * 
	 * @return The name that identify's the variable
	 */
	public final String getName(){
		return name;
	}
	/**
	 * 
	 * @return The current value of the variable
	 */
	public final int getValue(){
		return value;
	}
	/**
	 * Adds the specified value to the variable's current value
	 * 
	 * @param toadd The value to add to the current value
	 */
	public final void plusEquals(final int toadd){
		value += toadd;
	}
	/**
	 * Subtracts the specified value from the variable's current value
	 * 
	 * @param tosub The value to subtract from the current value
	 */
	public final void subEquals(final int tosub){
		value -= tosub;
	}
	/**
	 * Multiplies the variable's current value buy the specified value
	 * 
	 * @param tomult The value to multiply the current value by
	 */
	public final void multEquals(final int tomult){
		value *= tomult;
	}
	/**
	 * Divides the variable's current value by the specified value
	 * 
	 * @param todev The value to divide the current value by
	 */
	public final void devEquals(final int todev){
		value /= todev;
	}
	/**
	 * Calculates the value plus the specified value. Does not change the variable's value.
	 * 
	 * @param toadd The amount to add
	 * @return The variables current value + toadd
	 */
	public final int getPlusValue(final int toadd){
		return value + toadd;
	}
	/**
	 * Calculates the value minus the specified value. Does not change the variable's value.
	 * 
	 * @param tosub The amount to subtract
	 * @return The value - tosub
	 */
	public final int getMinusValue(final int tosub){
		return value - tosub;
	}
	/**
	 * Calculates the value multiplied by the specified value. Does not change the variable's value.
	 * 
	 * @param tomult The value to multiply by
	 * @return The value * tomult
	 */
	public final int getMultValue(final int tomult){
		return value * tomult;
	}
	/**
	 * Calculates the value divided by the specified value. Does not change the variable's value.
	 * 
	 * @param todev The amount to divide by
	 * @return The variables value / todev
	 */
	public final int getDevValue(final int todev){
		return value / todev;
	}
	/**
	 * Changes the variables current value to the specified value
	 * 
	 * @param value The new value
	 */
	public final void setValue(final int value){
		this.value = value;
	}
}