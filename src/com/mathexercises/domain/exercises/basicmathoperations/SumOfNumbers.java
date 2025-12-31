package com.mathexercises.domain.exercises.basicmathoperations;

public class SumOfNumbers extends BasicMathOperation{
	public SumOfNumbers() {
		super(
			"SOMA DE NÚMEROS", 
			"Soma com números aleatórios", 
			'+'
		);
	}

	@Override
	protected void calculateResult() {
		super.expression.sumNumbers();
	}
}
