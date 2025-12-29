package com.mathexercises.dto.responsewhile;

public abstract class ResponseToWhile {
	public static ResponseWhile exit() {
		return new ResponseWhile(true, false);
	}
	
	public static ResponseWhile repeat() {
		return new ResponseWhile(false, true);
	}
	
	public static ResponseWhile nextIteration() {
		return new ResponseWhile(false, false);
	}
}
