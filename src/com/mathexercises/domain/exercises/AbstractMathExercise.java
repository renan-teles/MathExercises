package com.mathexercises.domain.exercises;

import com.mathexercises.domain.exercises.difficults.Difficulty;
import com.mathexercises.domain.math.MathExpression;
import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.service.ConsoleService;
import com.mathexercises.singleton.ConsoleServiceSingleton;

public abstract class AbstractMathExercise implements MathExercise{
	protected final ConsoleService console = ConsoleServiceSingleton.inject();
	protected final MathExpression expression = new MathExpression();
	protected final String EXIT = "exit";

	protected String name;
	protected String description;
	protected Difficulty difficulty;
	
	public AbstractMathExercise(
		String name, 
		String description
	) {
		super();
		this.name = name.toUpperCase();
		this.description = this.capitalize(description);
	}
	
	protected abstract ResponseWhile exerciseLogic();
	
	@Override
	public void setQuantityNumbers(int quantity) {
		this.expression.setQuantityNumbers(quantity);
	}

	@Override
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public final void execute() {
		this.showHeader();
		
		int n = 1;
		int iterations = this.difficulty.getIterationsNumber();
		
		while(n <= iterations) {
			this.console.line();
			this.console.topic(n + " de " + iterations);
			this.console.newLine();
			
			ResponseWhile res = this.exerciseLogic();
			if(res.exit()) break;
			else if(res.repeat()) continue;
			
			n++;
		}
	}
	
	private void showHeader() {
		this.console.title(this.name);
		this.console.message(this.description);
		this.console.message("Dificuldade: " + this.difficulty.getName());
		this.console.newLine();
		this.console.topic("Digite '" + this.EXIT + "' para sair.");
		this.console.newLine();
	}
	
	private String capitalize(String s) {
		return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
	}
}
