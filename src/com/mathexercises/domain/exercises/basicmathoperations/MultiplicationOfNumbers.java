package com.mathexercises.domain.exercises.basicmathoperations;

public class MultiplicationOfNumbers extends BasicMathOperation{
	public MultiplicationOfNumbers() {
		super(
			"MULTIPLICAÇÃO DE NÚMEROS", 
			"Multiplicaçao com números aleatórios", 
			'*'
		);
	}

	@Override
	protected void calculateResult() {
		super.expression.multiplicationNumbers();
	}
}

