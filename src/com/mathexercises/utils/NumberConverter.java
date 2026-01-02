package com.mathexercises.utils;

import java.util.Optional;

public abstract class NumberConverter {
	public static Optional<Float> parseStringToFloat(String numString) {
   	  	try {
             return Optional.of(Float.parseFloat(numString.trim()));
         } catch (NumberFormatException e) {
             return Optional.empty();
         }
	 }
	
	public static Optional<Double> parseStringToDouble(String numString) {
   	  	try {
             return Optional.of(Double.parseDouble(numString.trim()));
         } catch (NumberFormatException e) {
             return Optional.empty();
         }
	 }
	 
	 public static Optional<Integer> parseStringToInt(String numString) {
		 try {
	        return Optional.of(Integer.parseInt(numString.trim()));
	     } catch (NumberFormatException e) {
	        return Optional.empty();
	     }
	 }
}
