package com.mathexercises.dto.responsewhile;

public abstract class ResponseToWhile {
	public static ResponseWhile exitLoop() {
		return new ResponseWhile(true, false);
	}
	
	public static ResponseWhile repeatCurrentIteration() {
		return new ResponseWhile(false, true);
	}
	
	public static ResponseWhile nextIteration() {
		return new ResponseWhile(false, false);
	}
}
