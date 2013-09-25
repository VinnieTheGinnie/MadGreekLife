package com.example.greeklife;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class GreekLetterConv{
	private HashMap<Character, String[]> greekLetters;
	
	public GreekLetterConv(){
		greekLetters = new HashMap<Character, String[]>();
		init();
	}
	
	/* 
	 * Converts a all the characters of argument to Strings.
	 * Does not add duplicates
	 * 	ex: aaa -> {alpha} and NOT {alpha, alpha, alpha}
	 */
	public HashSet<String> getFullName(String s){
		
		HashSet<String> letters = new HashSet<String>();
		for (char c : s.toCharArray()){
			for (String d : convertLetter(c)){
				letters.add(d);
			}
		}
		
		return letters;
	}
	
	/*converting a single letter*/
	public ArrayList<String> convertLetter(char s){
		ArrayList<String> result = new ArrayList<String>();
		if (greekLetters.get(s) != null){
			Collections.addAll(result, greekLetters.get(s));
		}
		return result;
	}
	
	private void init(){
		greekLetters.put('a', new String[]{"alpha"});
		greekLetters.put('b', new String[]{"beta"});
		greekLetters.put('g', new String[]{"gamma"});
		greekLetters.put('d', new String[]{"delta"});
		greekLetters.put('e', new String[]{"epsilon", "eta"});
		greekLetters.put('z', new String[]{"zeta"});
		greekLetters.put('t', new String[]{"theta"});
		greekLetters.put('i', new String[]{"iota"});
		greekLetters.put('k', new String[]{"kappa"});
		greekLetters.put('l', new String[]{"lambda"});
		greekLetters.put('m', new String[]{"mu"});
		greekLetters.put('n', new String[]{"nu"});
		greekLetters.put('x', new String[]{"xi"});
		greekLetters.put('o', new String[]{"omicron", "omega"});
		greekLetters.put('p', new String[]{"phi","pi", "psi"});	
		greekLetters.put('r', new String[]{"rho"});
		greekLetters.put('s', new String[]{"sigma"});
		greekLetters.put('t', new String[]{"tau"});
		greekLetters.put('c', new String[]{"chi"});
		greekLetters.put('u', new String[]{"upsilon"});
		
	}
	
}