package com.mathexercises.domain.exercises.basicmathoperations;

import com.mathexercises.service.ConsoleService;

public class SubtractionOfNumbers extends BasicMathOperation{
	public SubtractionOfNumbers(ConsoleService console) {
		super(
			"SUBTRAÇÃO DE NÚMEROS", 
			"Subtração com números aleatórios", 
			console, 
			'-'
		);
	}

	@Override
	protected void calculateResult() {
		super.validateRandomNumbers();
		super.expressionResult = super.randomNumbers
			.stream()
			.reduce((a, b) -> a - b)
			.get();
	}
}
