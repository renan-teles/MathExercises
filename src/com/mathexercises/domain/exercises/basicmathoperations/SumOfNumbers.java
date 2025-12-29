package com.mathexercises.domain.exercises.basicmathoperations;

import com.mathexercises.service.ConsoleService;

public class SumOfNumbers extends BasicMathOperation{
	public SumOfNumbers(ConsoleService console) {
		super(
			"SOMA DE NÚMEROS", 
			"Soma com números aleatórios", 
			console, 
			'+'
		);
	}

	@Override
	protected void calculateResult() {
		super.validateRandomNumbers();
		super.expressionResult = super.randomNumbers
			.stream()
			.reduce((a,b) -> a + b)
			.get();
	}
}
