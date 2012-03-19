package org.language.methods;

import org.language.variable.Variable;

public class Commands {
	
	public static void Command(final String line){
		String cmd = line.split(" ")[0];
		if(cmd.equals("println")){
			print(line, true);
		} else if(cmd.equals("print")){
			print(line, false);
		}
	}
	public static void print(final String line, boolean enter) {
		String printval = getStringValue(line.substring(enter ? 8 : 6));
		if(enter){
			System.out.println(printval);
		} else {
			System.out.print(printval);
		}
	}
	private static String getStringValue(String line){
		String ret = "";
		String[] words = line.split(":");
		for(String w : words){
			if(w.startsWith("\"") && w.endsWith("\"")){
				w = w.replaceAll("\"", "");
				ret += w.substring(0, w.length());
			} else if(Variable.exists(w)){
				ret += Variable.getValue(w);
			}
		}
		return ret;
	}
}