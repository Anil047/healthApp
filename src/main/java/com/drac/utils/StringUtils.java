package com.drac.utils;

public class StringUtils {

	public static boolean isNull(String word){
		if(word !=null){
			if(word.trim().length()!=0 || !"null".equalsIgnoreCase(word))
				return false;
		}
		return true;
	}
	
	public static boolean isNotNull(String word){
		return !isNull(word);
	}

}
