package com.mathexercises.domain.math;

import java.math.BigDecimal;
import java.util.List;

class MathExpressionBuilder {
	private String expression = "";
	
	public String getExpression() {
		return this.expression;
	}
	
	public void clearExpression() {
		this.expression = "";
	}
	
	public void buildExpression(
		List<BigDecimal> numbers, 
		char symbol
	) {		
		if(this.expressionIsSet()) return;
			
        StringBuilder sb = new StringBuilder();
        sb.append(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            sb.append(" ")
            .append(symbol)
            .append(" ")
            .append(numbers.get(i));
        }

        this.expression = sb.toString();
	}
	
	public void buildExpression(
		List<BigDecimal> numbers, 
		char symbol, 
		int maskNumberPosition
	) {
		if(this.expressionIsSet()) return;
		
		final char MASK = '?';
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < numbers.size(); i++) {
			if(maskNumberPosition == 0 && i == 0) {
				sb.append(MASK);
				continue;
			}
			if(i == 0) {
				sb.append(numbers.get(0));
				continue;
			}
			if(maskNumberPosition == i) {
				sb.append(" ")
				.append(symbol)
				.append(" ")
				.append(MASK);
				continue;
			}
			sb.append(" ")
			.append(symbol)
			.append(" ")
			.append(numbers.get(i));
		}
		
		this.expression = sb.toString();
	}
	
	private boolean expressionIsSet() {
		return !this.expression.isBlank();
	}
}
