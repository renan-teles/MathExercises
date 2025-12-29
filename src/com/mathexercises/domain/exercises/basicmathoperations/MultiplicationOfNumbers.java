package com.mathexercises.domain.exercises.basicmathoperations;

import com.mathexercises.service.ConsoleService;

public class MultiplicationOfNumbers extends BasicMathOperation{
	public MultiplicationOfNumbers(ConsoleService console) {
		super(
			"MULTIPLICAÇÃO DE NÚMEROS", 
			"Multiplicaçao com números aleatórios", 
			console, 
			'*'
		);
	}

	@Override
	protected void calculateResult() {
		super.validateRandomNumbers();
		super.expressionResult = super.randomNumbers
			.stream()
			.reduce((a, b) -> a * b)
			.get();
	}
}

