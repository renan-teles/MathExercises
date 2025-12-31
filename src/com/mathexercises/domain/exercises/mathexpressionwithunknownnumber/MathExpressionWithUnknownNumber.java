package com.mathexercises.domain.exercises.mathexpressionwithunknownnumber;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.mathexercises.domain.exercises.MathExercise;
import com.mathexercises.dto.responsewhile.ResponseToWhile;
import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.singleton.RandomSingleton;
import com.mathexercises.utils.NumberConverter;

public class MathExpressionWithUnknownNumber extends MathExercise{
	private Integer randomNumber = null;
	private List<MathOperation> operations;
	private MathOperation currentOperation = null;
	private Random random;
	
	public MathExpressionWithUnknownNumber(
		List<MathOperation> operations
	) {
		super(
			"EXPRESSÃO MATEMÁTICA COM NÚMERO DESCONHECIDO", 
			"Descubra o número desconhecido da expressão"
		);
		this.operations = operations;
		this.random = RandomSingleton.inject();
	}
	
	@Override
	protected ResponseWhile exerciseLogic() {
		this.defineRandomNumber();
		this.defineRandomOperation();
		
		this.expression.defineNumbers(this.difficulty, false);
		int position = this.expression.addNumberInRandomPosition(this.randomNumber);
		this.expression.generateExpression(this.currentOperation.getSymbol(), position);	
		
		this.currentOperation.calculateResult(this.expression);
		
		StringBuilder sb = new StringBuilder();
		sb.append(this.expression.getExpression());
		sb.append(" = ");
		sb.append(this.expression.getResult());
		this.console.message(sb.toString());
		
		Optional<String> userEntry = this.console.inputString("Qual o número desconhecido (?)");
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}
		if(userEntry.get().equalsIgnoreCase(super.EXIT)) {
			this.reset();
			return ResponseToWhile.exit();
		}
		
		Optional<Integer> optRes = NumberConverter.parseStringToInt(userEntry.get());
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
	}
}
