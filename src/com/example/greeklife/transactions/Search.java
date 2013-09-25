package com.example.greeklife.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.example.greeklife.GreekLetterConv;

public class Search {
	static Pattern regex = Pattern.compile("\\s*,\\s*");
	static GreekLetterConv glc = new GreekLetterConv();
	
	public Search(){
		
	}
	//uses the regex to turn the arg into an arraylist of strings
	private static ArrayList<String> getTags(String s){
		ArrayList<String> matches = new ArrayList<String>(); 
		ArrayList<String> extra = new ArrayList<String>();
		
		Collections.addAll(matches, s.split(regex.pattern()));
		
		for (String result : matches){
			if (result.length() == 2 || result.length() == 3){
				for (String a : glc.getFullName(result)){
					extra.add(a);
				}
			}
		}
		matches.addAll(extra);
		return matches;
	}
	
	//returns a number between 0 - 100. 100 = good match, 0 = no matches
	//input is 2 lists of strings
	private static int match(ArrayList<String> l1, ArrayList<String> l2){
		double min = Math.min(l1.size(), l2.size());
		l1.retainAll(l2);
		
		min = l1.size()/min;
		return (int)(min*100);
	}
	
	private static int match(String s, String t){
		return match(getTags(s), getTags(t));
	}
	
	//args[0] = HashMap<Name of Frat, Tags>
	//args[1] = Search string of tags
	public static ArrayList<String> match(HashMap<String, String> arg, String search){
		ArrayList<String> matches = new ArrayList<String>();
		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		name.addAll(arg.keySet());
		
		for (String a : name){
			tags.add(arg.get(a));
		}
		
		ArrayList<Integer> weights = new ArrayList<Integer>();
		
		
		for (String s : tags){
			weights.add(match(s, search));
		}
		
		//this seems really unnecessary, but it was the easiest way to sort
		for (int x = 100; x > 0; x--){
			while (weights.contains(x)){
				int index = weights.indexOf(x);
				matches.add(name.get(index));
				weights.remove(index);
				tags.remove(index);
				name.remove(index);
			}
		}
		
		return matches;
	}
	
}
