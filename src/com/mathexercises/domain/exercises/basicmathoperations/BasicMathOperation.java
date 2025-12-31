package com.mathexercises.domain.exercises.basicmathoperations;

import java.util.Optional;

import com.mathexercises.domain.exercises.MathExercise;
import com.mathexercises.dto.responsewhile.ResponseToWhile;
import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.utils.NumberConverter;

public abstract class BasicMathOperation extends MathExercise{
	protected char symbol;
	
	public BasicMathOperation(
		String name, 
		String description, 
		char symbol
	) {
		super(name, description);
		this.symbol = symbol;
	}
	
	@Override
	protected final ResponseWhile exerciseLogic() {
		super.expression.defineNumbers(this.difficulty, true);
		super.expression.generateExpression(this.symbol);
		
		this.console.message(this.expression.getExpression());

		Optional<String> userEntry = this.console.inputString("Resultado");
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}
		if(userEntry.get().equalsIgnoreCase(super.EXIT)) {
			this.expression.clearExpression();
			return ResponseToWhile.exit();
		}
		
		Optional<Integer> optRes = NumberConverter.parseStringToInt(userEntry.get());
		if(optRes.isEmpty()) {
			this.console.alert("Valor inválido. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		
		this.calculateResult();
		if(!this.expression.isCorrect(optRes.get())) {
			super.console.unsuccess("Você errou. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		super.console.success("VOCÊ ACERTOU!");
		this.expression.clearExpression();
		
		return ResponseToWhile.nextIteration();
	}
	
	protected abstract void calculateResult();
}
