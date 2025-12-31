package com.mathexercises.domain.exercises.mathexpressionwithunknownnumber;

import com.mathexercises.management.MathExpressionManagement;

public abstract class MathOperation {
	protected char symbol;
	protected String name;

	public MathOperation(char symbol, String name) {
		super();
		this.symbol = symbol;
		this.name = name.toLowerCase();
	}

	public char getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public abstract void calculateResult(MathExpressionManagement expression);
}
