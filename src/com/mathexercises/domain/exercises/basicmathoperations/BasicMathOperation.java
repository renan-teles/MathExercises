package com.mathexercises.domain.exercises.basicmathoperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mathexercises.domain.exercises.MathExercise;
import com.mathexercises.dto.responsewhile.ResponseToWhile;
import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.exceptions.InvalidRandomNumbersException;
import com.mathexercises.service.ConsoleService;
import com.mathexercises.utils.NumberConverter;

public abstract class BasicMathOperation extends MathExercise{
	protected List<Integer> randomNumbers = new ArrayList<>(10);
	protected int expressionResult;
	protected char symbol;
	
	private StringBuilder expression;
	private int randomNumbersQuantity = 2;
	
	public BasicMathOperation(
		String name, 
		String description, 
		ConsoleService console,
		char symbol
	) {
		super(name, description, console);
		this.symbol = symbol;
	}

	@Override
	protected final ResponseWhile exerciseLogic() {
		if(this.randomNumbers.isEmpty()) {
			this.defineRandomNumbers();
			this.generateExpression();
			this.calculateResult();
		}
		
		this.console.message(this.expression.toString());

		Optional<String> userEntry = this.console.inputString("Resultado");
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}
		if(userEntry.get().equalsIgnoreCase(super.EXIT)) {
			return ResponseToWhile.exit();
		}
		
		Optional<Integer> optRes = NumberConverter.parseStringToInt(userEntry.get());
		if(optRes.isEmpty()) {
			this.console.alert("Valor inválido. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		if(optRes.get() != this.expressionResult) {
			super.console.unsuccess("Você errou. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		
		super.console.success("VOCÊ ACERTOU!");
		this.randomNumbers.clear();
		
		return ResponseToWhile.nextIteration();
	}
	
	private void generateExpression() {
		this.validateRandomNumbers();
		
		StringBuilder expression = new StringBuilder();
		expression.append(this.randomNumbers.get(0));
		for(int i = 1; i < this.randomNumbers.size(); i++) {
			expression.append(" ");
			expression.append(this.symbol);
			expression.append(" ");
			expression.append(this.randomNumbers.get(i));
		}
		
		this.expression = expression;
	}
	
	protected abstract void calculateResult();

	public void setRandomQuantityNumbers(int quantityNumbers) {
		if(quantityNumbers >= 2 && quantityNumbers <= 10) {
			this.randomNumbersQuantity = quantityNumbers;
			return;
		} 
		throw new IllegalArgumentException(
			"Quantidade de números aleatórios inválida."
		);
	}
	
	private void defineRandomNumbers() {
		for(int i = 0; i < this.randomNumbersQuantity; i++) {
			this.randomNumbers.add(this.difficulty.getRandomNumber());
		}
	}
	
	protected void validateRandomNumbers() {
		int size = this.randomNumbers.size();
		if(
			!this.randomNumbers.isEmpty() 
			&& (size <= 10 && size == this.randomNumbersQuantity)
		) return;
		
		throw new InvalidRandomNumbersException();
	}
}
