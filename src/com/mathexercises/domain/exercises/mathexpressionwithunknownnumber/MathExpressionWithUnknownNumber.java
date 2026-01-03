package com.mathexercises.domain.exercises.mathexpressionwithunknownnumber;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.mathexercises.domain.exercises.AbstractMathExercise;
import com.mathexercises.domain.math.operations.MathOperation;
import com.mathexercises.dto.responseexerciselogic.ResponseExerciseLogic;
import com.mathexercises.dto.responseexerciselogic.ResponseToExerciseLogic;
import com.mathexercises.singleton.RandomSingleton;

public class MathExpressionWithUnknownNumber extends AbstractMathExercise{
	private final Random random = RandomSingleton.inject();
	private final List<MathOperation> operations;

	private Integer randomNumber = null, position = null;
	private MathOperation currentOperation = null;
	
	public MathExpressionWithUnknownNumber(
		List<MathOperation> operations
	) {
		super(
			"EXPRESSÃO MATEMÁTICA COM NÚMERO DESCONHECIDO", 
			"Descubra o número desconhecido da expressão",
			"Qual o número desconhecido (?)"
		);
		this.operations = operations;
	}
	
	@Override
	protected final void defineExpression() {
		this.defineRandomNumber();
		this.defineRandomOperation();
		this.setExpression();

		this.currentOperation.calculateResult(this.expression);
		this.console.message(
			new StringBuilder()
				.append(this.expression.getExpression())
				.append(" = ")
				.append(this.expression.getResult())
				.toString()
		);
	}
	
	@Override
	protected final ResponseExerciseLogic<Void> checkIsCorrect(BigDecimal value){
		if(value.compareTo(BigDecimal.valueOf(this.randomNumber)) != 0) {
			super.console.unsuccess("Você errou. Tente novamente.");
			return ResponseToExerciseLogic.repeatRound();
		}
		
		super.console.success("VOCÊ ACERTOU!");
		this.reset();
		return ResponseToExerciseLogic.nextRound();
	}
	
	private void setExpression() {
		if(this.position != null) return;
		
		this.position = this.expression.defineNumbers(
			this.difficulty, 
			BigDecimal.valueOf(this.randomNumber)
		);
		super.expression.generateExpression(
			this.currentOperation.operation(), 
			this.position
		);	
	}
	
	private void defineRandomNumber() {
		if(this.randomNumber != null) return;
		this.randomNumber = super.difficulty.getRandomNumber();
	}

	private void defineRandomOperation() {
		if(this.currentOperation != null) return;
		int randomPosition = this.random.nextInt(0, this.operations.size() - 1);
		this.currentOperation = this.operations.get(randomPosition);
	}
	
	private void reset() {
		this.expression.clearExpression();
		this.randomNumber = null;
		this.currentOperation = null;
		this.position = null;
	}
}
