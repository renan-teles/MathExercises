package com.mathexercises.utils;

import java.math.BigDecimal;
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
	
	public static Optional<BigDecimal> parseStringToBigDecimal(String numString) {
		Optional<Double> opt = NumberConverter.parseStringToDouble(numString);
		if(opt.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(BigDecimal.valueOf(opt.get()));
	 }
	 
	 public static Optional<Integer> parseStringToInt(String numString) {
		 try {
	        return Optional.of(Integer.parseInt(numString.trim()));
	     } catch (NumberFormatException e) {
	        return Optional.empty();
	     }
	 }
}
