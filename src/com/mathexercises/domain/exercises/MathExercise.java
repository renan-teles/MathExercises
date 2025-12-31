package com.mathexercises.domain.exercises;

import com.mathexercises.domain.exercises.difficults.DifficultyExercise;
import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.management.MathExpressionManagement;
import com.mathexercises.service.ConsoleService;
import com.mathexercises.singleton.ConsoleServiceSingleton;

public abstract class MathExercise {
	protected String name;
	protected String description;
	protected DifficultyExercise difficulty;
	protected String EXIT = "exit";
	
	protected MathExpressionManagement expression;
	protected ConsoleService console;

	public MathExercise(
		String name, 
		String description
	) {
		super();
		this.name = name.toUpperCase();
		this.description = this.capitalize(description);
		this.console = ConsoleServiceSingleton.inject();
		this.expression = new MathExpressionManagement();
	}

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
	
	protected abstract ResponseWhile exerciseLogic();
	
	private void showHeader() {
		this.console.title(this.name);
		this.console.message(this.description);
		this.console.message("Dificuldade: " + this.difficulty.getName());
		this.console.newLine();
		this.console.topic("Digite '" + this.EXIT + "' para sair.");
		this.console.newLine();
	}
	
	public void setQuantityNumbers(int quantity) {
		this.expression.setQuantityNumbers(quantity);
	}

	public void setDifficulty(DifficultyExercise difficulty) {
		this.difficulty = difficulty;
	}
	
	public String getName() {
		return this.name;
	}
	
	private String capitalize(String s) {
		return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
	}
}
