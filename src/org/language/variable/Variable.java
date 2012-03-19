package org.language.variable;

import java.util.Scanner;

import org.language.restricted.KeyWords;
import org.language.script.Script;
import org.language.variable.exceptions.VariableSyntaxException;
import org.language.variable.exceptions.VariableValueException;

public class Variable {
	
	private final String definition;
	
	private String type;
	
	private String name;
	
	private boolean initalized = false;
	
	private String value = "null";
	
	private boolean goodValue = false;
	
	private int intval;
	
	private String stringval;
	
	private char charval;
	
	private boolean boolvalue;
	
	private String orig;
	
	public Variable(final String definition) throws Exception {
		orig = definition;
		this.definition = definition.replaceAll("	", " ");
		define();
	}
	private final void define() throws Exception {
		if(getLastChar() != ';'){
			throw new VariableSyntaxException("Missing semi Colon.");
		} else if(!getFirstWord().equals(KeyWords.KW_VARIABLE)){
			throw new VariableSyntaxException("Missing keyword: var");
		} else if(!isValidType()){
			throw new VariableSyntaxException("The declared type is invalid.");
		}
		setName();
		for(Variable var : Script.getVariables()){
			if(var.getName().equals(name)){
				throw new VariableSyntaxException("Multiple variables with the same name");
			}
		}
		type = getType();
		initalized = definition.contains("=");
		if(initalized){
			if(definition.split("=").length > 2){
				throw new VariableSyntaxException("Multiple Equal signs found on initalization.");
			}
			setValue();
			parseValue();
		}
		add();
	}
	public String getName(){
		return name;
	}
	private void parseValue() throws Exception {
		if(getValue().equals(KeyWords.KW_READ)){
			value = readVal();
		}
		if(getType().equals(KeyWords.KW_BOOLEAN)){
			if(!value.contains("false") || !value.contains("true")){
				for(BooleanVar bv : Script.getBooleanVars()){
					if(bv.getName().equals(value)){
						boolvalue = bv.getValue();
						return;
					}
				}
			}
			boolvalue = Boolean.parseBoolean(value);
		} else if(getType().equals(KeyWords.KW_INTEGER)){
			try {
				intval = java.lang.Integer.parseInt(value);
			} catch(Exception e){
				boolean found = false;
				for(IntegerVar in : Script.getIntVars()){
					if(in.getName().equals(value)){
						intval = in.getValue();
						found = true;
					}
				}
				if(!found){
					throw new VariableSyntaxException("Variable: "+value+" could not be located.");
				}
			}
		} else if(getType().equals(KeyWords.KW_STRING)){
			if(!value.contains("\"")){
				for(StringVar str : Script.getStringVars()){
					if(str.getName().equals(value)){
						stringval = str.getValue();
						return;
					}
				}
			}
			int i1 = -1;
			int i2 = -1;
			char[] chars = getOtherValue().toCharArray();
			int co = 0;
			for(char c : chars){
				if(i1 == -1 && c == '\"'){
					i1 = co;
				} else if(i2 == -1 && c == '\"'){
					i2 = co;
				}
				co++;
			}
			stringval = getOtherValue().substring(i1+1,i2);
		} else if(getType().equals(KeyWords.KW_CHARACTER)){
			if(!value.contains("'")){
				for(CharVar var : Script.getCharVars()){
					if(var.getName().equals(value)){
						charval = var.getValue();
						return;
					}
				}
				throw new VariableValueException("No Value Found");
			}
			String sa = value.replaceAll("'", "");
			if(sa.length() > 1){
				throw new VariableValueException("More than one character found!");
			} else if(sa.length() == 0){
				throw new VariableValueException("No Value Found!");
			} else {
				charval = sa.toCharArray()[0];
			}
		}
	}
	/**
	 * If all goes well adds the variable to the list
	 */
	public void add(){
		if(getType().equals(KeyWords.KW_BOOLEAN)){
			Script.addBooleanVar(new BooleanVar(name, boolvalue));
		} else if(getType().equals(KeyWords.KW_CHARACTER)){
			Script.addCharVar(new CharVar(name, charval));
		} else if(getType().equals(KeyWords.KW_INTEGER)){
			Script.addIntVar(new IntegerVar(name, intval));
		} else if(getType().equals(KeyWords.KW_STRING)){
			Script.addStringVar(new StringVar(name, stringval));
		} else {
			return;
		}
		Script.addVar(this);
	}
	public String getOtherValue(){
		int subV = definition.split("=")[0].length()+1;
		return definition.substring(subV).replaceAll(";", "");
	}
	private void setValue(){
		int subV = definition.split("=")[0].length()+1;
		value = definition.substring(subV).replaceAll(" ", "").replaceAll(";", "");
	}
	private void setName(){
		name = definition.split(" ")[2];
	}
	private String getFirstWord(){
		return definition.split(" ")[0];
	}
	private String getType(){
		return definition.split(" ")[1];
	}
	private char getLastChar(){
		String[] wordList = definition.split(" ");
		return wordList[wordList.length-1].substring(wordList[wordList.length-1].length()-1).toCharArray()[0];
	}
	private boolean isValidType(){
		String type = getType();
		return type.equals(KeyWords.KW_BOOLEAN) || type.equals(KeyWords.KW_CHARACTER) || type.equals(KeyWords.KW_INTEGER) || type.equals(KeyWords.KW_STRING);
	}
	public boolean isInitalized(){
		return initalized;
	}
	public String getValue(){
		return value;
	}
	public String getVarType(){
		return type;
	}
	public void setValue(final String value) throws Exception {
		if(getType().equals(KeyWords.KW_INTEGER)){
			int nval = 0;
			try {
				nval = Integer.parseInt(value);
			} catch(Exception e){
				for(IntegerVar iv : Script.getIntVars()){
					if(iv.getName().equals(value)){
						nval = iv.getValue();
					}
				}
			}
			for(IntegerVar iv : Script.getIntVars()){
				if(iv.getName().equals(getName())){
					intval = nval;
					iv.setValue(nval);
					return;
				}
			}
			throw new VariableSyntaxException("Variable not found");
		} else if(getType().equals(KeyWords.KW_BOOLEAN)){
			if(value.endsWith("true") || value.endsWith("false")){
				boolvalue = Boolean.parseBoolean(value);
				for(BooleanVar bv2 : Script.getBooleanVars()){
					if(bv2.getName().equals(getName())){
						bv2.setValue(boolvalue);
						return;
					}
				}
			} else {
				for(BooleanVar bv : Script.getBooleanVars()){
					if(bv.getName().equals(value)){
						boolvalue = bv.getValue();
						for(BooleanVar bv2 : Script.getBooleanVars()){
							if(bv2.getName().equals(getName())){
								bv2.setValue(boolvalue);
								return;
							}
						}
					}
				}
				throw new VariableValueException("");
			}
		} else if(getType().equals(KeyWords.KW_CHARACTER)){
			
		} else if(getType().equals(KeyWords.KW_STRING)){
			if(!value.contains("\"")){
				for(StringVar str : Script.getStringVars()){
					if(str.getName().equals(value)){
						stringval = str.getValue();
						str.setValue(str.getValue());
						return;
					}
				}
			}
			stringval = value.replaceAll("\"", "");
			for(StringVar str : Script.getStringVars()){
				if(str.getName().equals(value)){
					str.setValue(stringval);
					return;
				}
			}
		}
	}
	public void addValue(final String value) throws Exception {
		if(getType().equals(KeyWords.KW_INTEGER)){
			int nval = 0;
			try {
				nval = Integer.parseInt(value);
			} catch(Exception e){
				for(IntegerVar iv : Script.getIntVars()){
					if(iv.getName().equals(value)){
						nval = iv.getValue();
					}
				}
			}
			for(IntegerVar iv : Script.getIntVars()){
				if(iv.getName().equals(getName())){
					intval = iv.getValue() + nval;
					iv.plusEquals(nval);
					return;
				}
			}
			throw new VariableSyntaxException("Variable not found");
		} else if(getType().equals(KeyWords.KW_STRING)){
			String ov = "";
			for(StringVar sv : Script.getStringVars()){
				if(sv.getName().equals(getName())){
					ov = sv.getValue();
				}
			}
			if(!value.contains("\"")){
				for(StringVar str : Script.getStringVars()){
					if(str.getName().equals(value)){
						stringval = ov+str.getValue();
						str.plusEquals(str.getValue());
						return;
					}
				}
			}
			stringval = ov+value.replaceAll("\"", "");
			for(StringVar str : Script.getStringVars()){
				if(str.getName().equals(value)){
					stringval = ov+str.getValue();
					str.plusEquals(value.replaceAll("\"", ""));
					return;
				}
			}
		} else {
			throw new VariableSyntaxException("Type mismatch");
		}
	}
	public void subValue(final String value) throws Exception {
		if(getType().equals(KeyWords.KW_INTEGER)){
			int nval = 0;
			try {
				nval = Integer.parseInt(value);
			} catch(Exception e){
				for(IntegerVar iv : Script.getIntVars()){
					if(iv.getName().equals(value)){
						nval = iv.getValue();
					}
				}
			}
			for(IntegerVar iv : Script.getIntVars()){
				if(iv.getName().equals(getName())){
					intval = iv.getValue() - nval;
					iv.subEquals(nval);
					return;
				}
			}
			throw new VariableSyntaxException("Variable not found");
		} else {
			throw new VariableSyntaxException("Type mismatch");
		}
	}
	public void devValue(final String value) throws Exception {
		if(getType().equals(KeyWords.KW_INTEGER)){
			int nval = 0;
			try {
				nval = Integer.parseInt(value);
			} catch(Exception e){
				for(IntegerVar iv : Script.getIntVars()){
					if(iv.getName().equals(value)){
						nval = iv.getValue();
					}
				}
			}
			for(IntegerVar iv : Script.getIntVars()){
				if(iv.getName().equals(getName())){
					intval = iv.getValue() / nval;
					iv.devEquals(nval);
					return;
				}
			}
			throw new VariableSyntaxException("Variable not found");
		} else {
			throw new VariableSyntaxException("Type mismatch");
		}
	}
	public void multValue(final String value) throws Exception {
		if(getType().equals(KeyWords.KW_INTEGER)){
			int nval = 0;
			try {
				nval = Integer.parseInt(value);
			} catch(Exception e){
				for(IntegerVar iv : Script.getIntVars()){
					if(iv.getName().equals(value)){
						nval = iv.getValue();
					}
				}
			}
			for(IntegerVar iv : Script.getIntVars()){
				if(iv.getName().equals(getName())){
					intval = iv.getValue() * nval;
					iv.multEquals(nval);
					return;
				}
			}
			throw new VariableSyntaxException("Variable not found");
		} else {
			throw new VariableSyntaxException("Type mismatch");
		}
	}
	public String getString(){
		if(getType().equals(KeyWords.KW_STRING)){
			String ret = "";
			for(StringVar ivar : Script.getStringVars()){
				if(ivar.getName().equals(getName())){
					ret += ivar.getValue(); 
				}
			}
			return ret;
		} else if(getType().equals(KeyWords.KW_BOOLEAN)){
			String ret = "";
			for(BooleanVar ivar : Script.getBooleanVars()){
				if(ivar.getName().equals(getName())){
					ret += ivar.getValue(); 
				}
			}
			return ret;
		} else if(getType().equals(KeyWords.KW_INTEGER)){
			String ret = "";
			for(IntegerVar ivar : Script.getIntVars()){
				if(ivar.getName().equals(getName())){
					ret += ivar.getValue(); 
				}
			}
			return ret;
		} else if(getType().equals(KeyWords.KW_CHARACTER)){
			String ret = "";
			for(CharVar ivar : Script.getCharVars()){
				if(ivar.getName().equals(getName())){
					ret += ivar.getValue(); 
				}
			}
			return ret;
		} else {
			return "";
		}
	}
	/**
	 * Determines if the specified variable exists
	 * @param name
	 * @return
	 */
	public static boolean exists(final String name){
		for(Variable var : Script.getVariables()){
			if(var.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	public static String getValue(final String name){
		for(Variable var : Script.getVariables()){
			if(var.getName().equals(name)){
				return var.getString();
			}
		}
		return "";
	}
	public void println(){
		if(getType().equals(KeyWords.KW_STRING)){
			System.out.println(stringval);
		} else if(getType().equals(KeyWords.KW_BOOLEAN)){
			System.out.println(boolvalue);
		} else if(getType().equals(KeyWords.KW_INTEGER)){
			System.out.println(intval);
		} else if(getType().equals(KeyWords.KW_CHARACTER)){
			System.out.println(charval);
		}
	}
	public static void display(String line) throws Exception {
		line = line.replace("	", " ");
		String[] lc = line.split(" ");
		if(lc[0].equals("display")){
			String varname = lc[1];
			for(Variable v : Script.getVariables()){
				if(v.getName().equals(varname)){
					v.println();
				}
			}
		} else {
			throw new Exception("Bad syntax.");
		}
	}
	public static void redefine(String definition) throws Exception {
		definition = definition.replaceAll("	", " ");
		String[] d = definition.split(" ");
		if(d[0].equals(KeyWords.KW_SET)){
			String[] wordList = definition.split(" ");
			char c = wordList[wordList.length-1].substring(wordList[wordList.length-1].length()-1).toCharArray()[0];
			if(c == ';'){
				String varName = wordList[1];
				for(Variable var : Script.getVariables()){
					if(var.getName().equals(varName)){
						String sign = wordList[2];
						int subV = definition.split("=")[0].length()+1;
						String value = definition.substring(subV).replaceAll(" ", "").replaceAll(";", "");
						if(value.equals(KeyWords.KW_READ)){
							value = readVal();
						}
						if(sign.equals("=")){
							var.setValue(value);
							return;
						} else if(sign.equals("+=")){
							var.addValue(value);
							return;
						} else if(sign.equals("-=")){
							var.subValue(value);
							return;
						} else if(sign.equals("*=")){
							var.multValue(value);
							return;
						} else if(sign.equals("/=")){
							var.devValue(value);
							return;
						}
					}
				}
				throw new VariableSyntaxException("Variable not found");
			} else {
				throw new VariableSyntaxException("Missing Semi Colon");
			}
		} else {
			throw new VariableSyntaxException("Error redefining variable");
		}
	}
	public static String readVal(){
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
}