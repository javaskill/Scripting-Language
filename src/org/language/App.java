/**
 * 
 */
package org.language;


import org.language.script.Script;

/**
 * @author Brad
 *
 */
public class App {
	
	private static final String[] code = {
		"println \"Enter The First number\"",
		"var int numone = read;",
		"println \"Enter the Second number\"",
		"var int numtwo = read;",
		"var int result = numone;",
		"set result /= numtwo;",
		"println \"The value of \":numone:\" divided by \":numtwo:\" is \":result"
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Script s = new Script(code);
		while(true){
			s.execute();
		}
	}
}