package com.mathexercises.domain.exercises.mathexpressionwithunknownnumber;

import com.mathexercises.management.MathExpressionManagement;

public class SubtractionMathOperation extends MathOperation{
	public SubtractionMathOperation() {
		super('-', "subtraction");
	}

	@Override
	public void calculateResult(MathExpressionManagement expression) {
		expression.subtractionNumbers();
	}
}
