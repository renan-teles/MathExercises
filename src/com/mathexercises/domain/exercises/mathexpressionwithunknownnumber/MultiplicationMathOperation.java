package com.mathexercises.domain.exercises.mathexpressionwithunknownnumber;

import com.mathexercises.management.MathExpressionManagement;

public class MultiplicationMathOperation extends MathOperation{
	public MultiplicationMathOperation() {
		super('*', "multiplication");
	}

	@Override
	public void calculateResult(MathExpressionManagement expression) {
		expression.multiplicationNumbers();
	}
}
