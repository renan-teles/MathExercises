package com.mathexercises.domain.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mathexercises.domain.exercises.difficults.Difficulty;
import com.mathexercises.domain.math.operations.Operation;
import com.mathexercises.exceptions.MathExpressionException;
import com.mathexercises.singleton.RandomSingleton;

public class MathExpression {
    private final List<BigDecimal> numbers = new ArrayList<>();
    private final MathExpressionBuilder builder = new MathExpressionBuilder();
    private final Random random = RandomSingleton.inject();

    private BigDecimal result = null;
    private int quantityNumbers = 2;

    public String getExpression() {
        return this.builder.getExpression();
    }

    public BigDecimal getResult() {
        return this.result;
    }

    public int getQuantityNumbers() {
    	return this.quantityNumbers;
    }
    
    public void setQuantityNumbers(int quantity) {
    	if(quantity >= 2 && quantity <= 10) {
    		this.quantityNumbers = quantity;
    		return;
    	}
    	throw new IllegalArgumentException(
    		"Quantidade inválida de números para a expressão."
        );  
    }

    public boolean isCorrect(BigDecimal value) {
        return value.compareTo(this.result) == 0;
    }

    public void clearExpression() {
        this.numbers.clear();
        this.builder.clearExpression();
        this.result = null;
    }

    public MathExpression addNumber(BigDecimal n) {
        this.numbers.add(n);
        return this;
    }

    public int addNumberInRandomPosition(BigDecimal n) {
        int index = this.random.nextInt(this.numbers.size() + 1);
        this.numbers.add(index, n);
        return index;
    }
    
    public MathExpression defineNumbers(Difficulty difficulty) {
    	this.defineNumbers(difficulty, true);
    	return this;
    }
    
    public int defineNumbers(Difficulty difficulty, BigDecimal number) {
    	this.defineNumbers(difficulty, false);
    	return this.addNumberInRandomPosition(number);
    }

    public MathExpression generateExpression(Operation operation) {
        this.validateNumbers();
        builder.buildExpression(numbers, operation.symbol());
        return this;
    }
    
    public MathExpression generateExpression(char symbol) {
        this.validateNumbers();
        builder.buildExpression(numbers, symbol);
        return this;
    }

    public MathExpression generateExpression(
    	Operation operation, int maskNumberPosition
    ) {
        this.validateNumbers();
        this.builder.buildExpression(
        	numbers, 
        	operation.symbol(), 
        	maskNumberPosition
        );
        return this;
    }

    public MathExpression calculate(Operation operation) {
        if(this.resultIsSet()) return this;
    	this.validateNumbers();

        this.result = this.numbers.stream()
            .reduce(operation::apply)
            .orElseThrow(() -> new MathExpressionException(
            		"Falha ao realizar operação matemática."
            	)
            );
        
        return this;
    }
    
    public MathExpression avg() {
    	 if(this.resultIsSet()) return this;
    	  
    	this.result 
    		= this.calculate(Operation.SUM)
    			.getResult()
    			.divide(
    				BigDecimal.valueOf(this.quantityNumbers), 
    				2, 
    				RoundingMode.HALF_UP
    			);
    	
    	return this;
    }
    
    private boolean resultIsSet() {
        return this.result != null;
    }
    
    private boolean numbersIsSet() {
    	return !this.numbers.isEmpty();
    }
    
    private void defineNumbers(Difficulty difficulty, boolean full) {
        if (this.numbersIsSet() || this.resultIsSet()) return;

        int end = full ? this.quantityNumbers : this.quantityNumbers - 1;
        for (int i = 0; i < end; i++) {
            this.numbers.add(
            	BigDecimal.valueOf(difficulty.getRandomNumber())
            );
        }
    }

    private void validateNumbers() {
    	int size = numbers.size();
        if(size >= 2 && size <= this.quantityNumbers) return;
        
        throw new MathExpressionException(
        	"Números inválidos para montagem da expressão."
        );
    }
}

