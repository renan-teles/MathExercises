package com.mathexercises.domain.exercises.basicmathoperations;

import java.math.BigDecimal;
import java.util.Optional;

import com.mathexercises.domain.exercises.AbstractMathExercise;
import com.mathexercises.domain.math.operations.Operation;
import com.mathexercises.dto.responsewhile.*;
import com.mathexercises.utils.NumberConverter;

public abstract class BasicMathOperation extends AbstractMathExercise{
	protected final Operation operation;
	
	public BasicMathOperation(
		String name, 
		String description,
		Operation operation
	) {
		super(name, description);
		this.operation = operation;
	}
	
	@Override
	protected ResponseWhile exerciseLogic() {
		super.expression.defineNumbers(this.difficulty);
		super.expression.generateExpression(this.operation);
		
		this.console.message(this.expression.getExpression());

		Optional<String> userEntry = this.console.inputString(
			"Resultado"
		);
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}
		else if(userEntry.get().equalsIgnoreCase(super.EXIT)) {
			this.expression.clearExpression();
			return ResponseToWhile.exit();
		}
		
		Optional<Double> optRes = NumberConverter.parseStringToDouble(
			userEntry.get()
		);
		if(optRes.isEmpty()) {
			this.console.alert("Valor inválido. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		
		this.calculateResult();
		if(!this.expression.isCorrect(BigDecimal.valueOf(optRes.get()))) {
			super.console.unsuccess("Você errou. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		super.console.success("VOCÊ ACERTOU!");
		this.expression.clearExpression();
		
		return ResponseToWhile.nextIteration();
	}
	
	protected final void calculateResult() {
		super.expression.calculate(this.operation);
	}
}
