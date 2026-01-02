package com.mathexercises.domain.math.operations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public enum Operation {
    SUM(BigDecimal::add, '+'),
    SUBTRACTION(BigDecimal::subtract, '-'),
    MULTIPLICATION(BigDecimal::multiply, '*'),
    DIVISION((a, b) -> a.divide(b, 2, RoundingMode.HALF_UP), '/');

    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;
    private final char symbol;

    Operation(
    	BiFunction<BigDecimal, BigDecimal, BigDecimal> operation, 
    	char symbol
    ) {
        this.operation = operation;
        this.symbol = symbol;
    }

    public BigDecimal apply(BigDecimal a, BigDecimal b) {
        return this.operation.apply(a, b);
    }

    public char symbol() {
        return this.symbol;
    }
}
