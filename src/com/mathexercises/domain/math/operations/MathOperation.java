package com.mathexercises.domain.math.operations;

import com.mathexercises.domain.math.MathExpression;

public abstract class MathOperation {
	protected Operation operation;

	public MathOperation(Operation operation) {
		super();
		this.operation = operation;
	}

	public char symbol() {
		return operation.symbol();
	}
	
	public Operation operation() {
		return this.operation;
	}

	public final void calculateResult(MathExpression expression) {
		expression.calculate(this.operation);
	}
}
