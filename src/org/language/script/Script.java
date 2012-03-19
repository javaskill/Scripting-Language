/**
 * 
 */
package org.language.script;

import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.util.Vector;

import org.language.methods.Commands;
import org.language.restricted.KeyWords;
import org.language.variable.BooleanVar;
import org.language.variable.CharVar;
import org.language.variable.IntegerVar;
import org.language.variable.StringVar;
import org.language.variable.Variable;

/**
 * @author Brad
 *
 */
public class Script {
	
	private static Vector<Variable> vars;
	private static Vector<IntegerVar> intvars;
	private static Vector<StringVar> stringvars;
	private static Vector<BooleanVar> booleanvars;
	private static Vector<CharVar> charvars;

	File file = null;
	
	private String[] code;
	
	public Script(final File file) {
		this.file = file;
	}
	public Script(final String[] code){
		this.code = code;
	}
	/**
	 * Executes the code
	 */
	public void execute() throws Exception {
		if(file != null){
			readFile();
		}
		vars = new Vector<Variable>();
		intvars = new Vector<IntegerVar>();
		stringvars = new Vector<StringVar>();
		booleanvars = new Vector<BooleanVar>();
		charvars = new Vector<CharVar>();
		for(String line : code){
			if(line.contains(KeyWords.KW_VARIABLE)){
				new Variable(line);
			} else if(line.contains(KeyWords.KW_SET)){
				Variable.redefine(line);
			}
			Commands.Command(line);
		}
	}
	public static void addStringVar(StringVar str){
		stringvars.add(str);
	}
	public static void addBooleanVar(BooleanVar boolvar){
		booleanvars.add(boolvar);
	}
	public static void addCharVar(CharVar charvar){
		charvars.add(charvar);
	}
	public static void addIntVar(IntegerVar in){
		intvars.add(in);
	}
	public static void addVar(Variable var){
		vars.add(var);
	}
	public void onFinish(){
		vars.removeAllElements();
		intvars.removeAllElements();
		stringvars.removeAllElements();
		booleanvars.removeAllElements();
		charvars.removeAllElements();
	}
	public void readFile() throws Exception {
		Vector<String> codes = new Vector<String>();
		if(!file.exists()){
			throw new FileNotFoundException("File could not be located at: "+file.getAbsolutePath());
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while((line = br.readLine()) != null){
			codes.add(line);
		}
		code = codes.toArray(new String[codes.size()]);
	}
	public static Vector<Variable> getVariables(){
		return vars;
	}
	public static Vector<IntegerVar> getIntVars(){
		return intvars;
	}
	public static Vector<StringVar> getStringVars(){
		return stringvars;
	}
	public static Vector<BooleanVar> getBooleanVars(){
		return booleanvars;
	}
	public static Vector<CharVar> getCharVars(){
		return charvars;
	}
}