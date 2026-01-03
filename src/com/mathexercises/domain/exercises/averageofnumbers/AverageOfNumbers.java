package com.mathexercises.domain.exercises.averageofnumbers;

import java.math.BigDecimal;

import com.mathexercises.domain.exercises.AbstractMathExercise;
import com.mathexercises.dto.responseexerciselogic.*;

public class AverageOfNumbers extends AbstractMathExercise{
	private final char symbol = ',';
	
	public AverageOfNumbers() {
		super(
			"MÉDIA ENTRE NÚMEROS", 
			"Média entre números aleatórios",
			"Média"
		);
	}
	
	@Override
	protected void showHeader() {
		this.console.title(this.name);
		this.console.message(this.description);
		this.console.message("Dificuldade: " + this.difficulty.getName());
		this.console.newLine();
		
		this.console.topic(
			"O resultado deve conter duas casas decimais após a vírgula."
		);
		this.console.topic(
			new StringBuilder()
				.append("Digite '")
				.append(this.EXIT)
				.append("' para sair.")
				.toString()
		);
		this.console.newLine();
	}
	
	@Override
	protected final void defineExpression() {
		super.expression
			.defineNumbers(this.difficulty)
			.generateExpression(this.symbol);
	
		this.console.message(
			new StringBuilder()
				.append("Média entre: ")
				.append(super.expression.getExpression())
				.toString()
		);
	}
	
	@Override
	protected final ResponseExerciseLogic<Void> checkIsCorrect(BigDecimal value){
		if(!this.expression.avg().isCorrect(value)) {
			super.console.unsuccess("Você errou. Tente novamente.");
			return ResponseToExerciseLogic.repeatRound();
		}
		
		super.console.success("VOCÊ ACERTOU!");
		this.expression.clearExpression();
		return ResponseToExerciseLogic.nextRound();
	}
}
