package com.mathexercises.domain.exercises.basicmathoperations;

import java.math.BigDecimal;

import com.mathexercises.domain.exercises.AbstractMathExercise;
import com.mathexercises.domain.math.operations.Operation;
import com.mathexercises.dto.responseexerciselogic.ResponseExerciseLogic;
import com.mathexercises.dto.responseexerciselogic.ResponseToExerciseLogic;

public abstract class BasicMathOperation extends AbstractMathExercise{
	protected final Operation operation;
	
	public BasicMathOperation(
		String name, 
		String description,
		Operation operation
	) {
		super(name, description, "Resultado");
		this.operation = operation;
	}
	
	@Override
	protected final void defineExpression() {
		this.console.message(
			super.expression
				.defineNumbers(this.difficulty)
				.generateExpression(this.operation)
				.getExpression()
		);
	}
	
	@Override
	protected final ResponseExerciseLogic<Void> checkIsCorrect(BigDecimal value){
		boolean isCorrect 
			= this.expression
				.calculate(this.operation)
				.isCorrect(value);
		
		if(!isCorrect) {
			super.console.unsuccess("Você errou. Tente novamente.");
			return ResponseToExerciseLogic.repeatRound();
		}
		super.console.success("VOCÊ ACERTOU!");
		this.expression.clearExpression();
		return ResponseToExerciseLogic.nextRound();
	}
}


