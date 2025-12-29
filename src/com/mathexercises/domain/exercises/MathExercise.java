package com.mathexercises.domain.exercises;

import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.service.ConsoleService;

public abstract class MathExercise {
	protected String name;
	protected String description;
	protected DifficultyExercise difficulty;
	protected ConsoleService console;
	protected String EXIT = "exit";
	
	public MathExercise(
		String name, 
		String description, 
		ConsoleService console
	) {
		super();
		this.name = name.toUpperCase();
		this.description = this.capitalize(description);
		this.console = console;
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
