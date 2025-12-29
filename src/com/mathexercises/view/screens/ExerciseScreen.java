package com.mathexercises.view.screens;

import java.util.Map;

import com.mathexercises.domain.exercises.DifficultyExercise;
import com.mathexercises.domain.exercises.MathExercise;
import com.mathexercises.service.ConsoleService;

public abstract class ExerciseScreen extends MainScreen{
	protected MathExercise exercise;
	protected Map<Integer, DifficultyExercise> difficults;
	
	public ExerciseScreen(
		ConsoleService console, 
		MathExercise exercise, 
		Map<Integer, DifficultyExercise> difficults
	) {
		super(exercise.getName(), console);
		this.exercise = exercise;
		
		this.validateDifficults(difficults);
		this.difficults = difficults;
	}
	
	protected final void showDifficults() {
		if(!this.difficults.isEmpty()) {
			this.difficults.forEach((k,d) -> {
				this.console.message(k + " - " + d.getName());
			});
		}
		this.console.message("0 - Sair/Voltar");
	}
	
	private void validateDifficults(Map<Integer, DifficultyExercise> difficults) {
		if(!difficults.isEmpty()) return;
		throw new IllegalArgumentException(
			"Dificuldades não definidas para exercício matemático."
		);
	}
}
