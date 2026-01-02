package com.mathexercises.domain.exercises.mathexpressionwithunknownnumber;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.mathexercises.domain.exercises.AbstractMathExercise;
import com.mathexercises.domain.math.operations.MathOperation;
import com.mathexercises.dto.responsewhile.*;
import com.mathexercises.singleton.RandomSingleton;
import com.mathexercises.utils.NumberConverter;

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
			"Descubra o número desconhecido da expressão"
		);
		this.operations = operations;
	}
		
	@Override
	protected ResponseWhile exerciseLogic() {
		this.defineRandomNumber();
		this.defineRandomOperation();
		this.defineExpression();

		this.currentOperation.calculateResult(this.expression);
		this.console.message(
			new StringBuilder()
				.append(this.expression.getExpression())
				.append(" = ")
				.append(this.expression.getResult())
				.toString()
		);
		
		Optional<String> userEntry = this.console.inputString(
			"Qual o número desconhecido (?)"
		);
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}
		else if(userEntry.get().equalsIgnoreCase(super.EXIT)) {
			this.reset();
			return ResponseToWhile.exit();
		}
		
		Optional<Integer> optRes = NumberConverter.parseStringToInt(
			userEntry.get()
		);
		if(optRes.isEmpty()) {
			this.console.alert("Valor inválido. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		
		if(optRes.get() != this.randomNumber) {
			super.console.unsuccess("Você errou. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		super.console.success("VOCÊ ACERTOU!");
		
		this.reset();
		return ResponseToWhile.nextIteration();
	}
	
	private void defineExpression() {
		if(this.position != null) return;
		
		this.position = this.expression.defineNumbers(
			this.difficulty, 
			BigDecimal.valueOf(this.randomNumber)
		);
		this.expression.generateExpression(
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
