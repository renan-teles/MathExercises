package com.mathexercises.domain.exercises.mathexpressionwithunknownnumber;

import com.mathexercises.management.MathExpressionManagement;

public class SumMathOperation extends MathOperation{
	public SumMathOperation() {
		super('+', "sum");
	}

	@Override
	public void calculateResult(MathExpressionManagement expression) {
		expression.sumNumbers();
	}
}
