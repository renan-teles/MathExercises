package com.mathexercises.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mathexercises.domain.exercises.difficults.DifficultyExercise;
import com.mathexercises.exceptions.BuildExpressionException;
import com.mathexercises.singleton.RandomSingleton;

public class MathExpressionManagement {
	private List<Integer> numbers = new ArrayList<>(10);
	private Random random = RandomSingleton.inject();
	private StringBuilder expression = null;
	private Integer result = null;
	private int quantityNumbers = 2;
	
	public String getExpression() {
		return expression.toString();
	}

	public Integer getResult() {
		return result;
	}
	
	public int getQuantityNumbers() {
		return quantityNumbers;
	}
	
	public void setQuantityNumbers(int quantityNumbers) {
		if(quantityNumbers >= 2 && quantityNumbers <= 10) {
			this.quantityNumbers = quantityNumbers;
			return;
		}
		throw new IllegalArgumentException(
			"Quantidade inválida de números para a montagem de expressão."
		);
	}
	
	public boolean resultIsSet() {
		return this.result != null;
	}
	
	public boolean isCorrect(int n) {
		return n == this.result;
	}
	
	public void clearExpression() {
		this.numbers.clear();
		this.result = null;
		this.expression = null;
	}
	
	public void addNumber(int n) {
		this.numbers.add(n);
	}

	public int addNumberInRandomPosition(int n) {
		int random = this.random.nextInt(0, this.numbers.size() + 1);
		this.numbers.add(random, n);
		return random;
	}
	
	public void defineNumbers(DifficultyExercise difficulty, boolean full) {
		if(this.resultIsSet()) return;
		
		int end = full? this.quantityNumbers : this.quantityNumbers - 1;
		for(int i = 0; i < end; i++) {
			this.numbers.add(difficulty.getRandomNumber());				
		}
	}
	
	public void generateExpression(char symbol) {
		if(this.resultIsSet()) return;
		this.validateNumbers();
		
		StringBuilder expression = new StringBuilder();
		
		expression.append(this.numbers.get(0));
		for(int i = 1; i < this.numbers.size(); i++) {
			expression.append(" ");
			expression.append(symbol);
			expression.append(" ");
			expression.append(this.numbers.get(i));
		}
		
		this.expression = expression;
	}
	
	public void generateExpression(char symbol, int maskNumberPosition) {
		if(this.resultIsSet()) return;
		this.validateNumbers();
		
		final char MASK = '?';
		StringBuilder expression = new StringBuilder();
		
		for(int i = 0; i < this.numbers.size(); i++) {
			if(maskNumberPosition == 0 && i == 0) {
				expression.append(MASK);
				continue;
			}
			if(i == 0) {
				expression.append(this.numbers.get(0));
				continue;
			}
			if(maskNumberPosition == i) {
				expression.append(" ");
				expression.append(symbol);
				expression.append(" ");
				expression.append(MASK);
				continue;
			}
			expression.append(" ");
			expression.append(symbol);
			expression.append(" ");
			expression.append(this.numbers.get(i));
		}
		
		this.expression = expression;
	}
	
	public void sumNumbers() {
		if(this.resultIsSet()) return;
		this.validateNumbers();
		this.result = this.numbers
			.stream()
			.reduce((a,b) -> a + b)
			.get();
	}
	
	public void subtractionNumbers() {
		if(this.resultIsSet()) return;
		this.validateNumbers();
		this.result = this.numbers
			.stream()
			.reduce((a,b) -> a - b)
			.get();
	}
	
	public void multiplicationNumbers() {
		if(this.resultIsSet()) return;
		this.validateNumbers();
		this.result = this.numbers
			.stream()
			.reduce((a,b) -> a * b)
			.get();
	}
	
	private void validateNumbers() {
		int size = this.numbers.size();
		if(this.numbers.isEmpty() || !(size >= 2 || size <= this.quantityNumbers)) {
			throw new BuildExpressionException(
				"Números não definidos corretamente para montagem de expressão."
			);
		}
	}
}
