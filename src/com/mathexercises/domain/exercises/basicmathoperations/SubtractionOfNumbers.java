package com.mathexercises.domain.exercises.basicmathoperations;

public class SubtractionOfNumbers extends BasicMathOperation{
	public SubtractionOfNumbers() {
		super(
			"SUBTRAÇÃO DE NÚMEROS", 
			"Subtração com números aleatórios", 
			'-'
		);
	}

	@Override
	protected void calculateResult() {
		super.expression.subtractionNumbers();
	}
}
