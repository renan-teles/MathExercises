package com.mathexercises.view.screens;

import java.util.Map;
import java.util.Optional;

import com.mathexercises.domain.exercises.MathExercise;
import com.mathexercises.domain.exercises.difficults.DifficultyExercise;
import com.mathexercises.dto.responsewhile.ResponseToWhile;
import com.mathexercises.dto.responsewhile.ResponseWhile;

public class ExerciseScreen extends MainScreen{
	protected MathExercise exercise;
	protected Map<Integer, DifficultyExercise> difficults;
	protected DifficultyExercise selectedDifficulty;
	protected int selectedQuantityRandomNumbers;
	
	public ExerciseScreen(
		MathExercise exercise, 
		Map<Integer, DifficultyExercise> difficults
	) {
		super(exercise.getName());
		this.exercise = exercise;
		
		this.validateDifficults(difficults);
		this.difficults = difficults;
	}
		
	@Override
	protected void showHeader() {
		super.console.title(
			"PREFERÊNCIAS PARA O EXERCÍCIO - " + super.title
		);
		super.console.subtopic(
			"Selecione a DIFICULDADE e a QUANTIDADE DE NÚMEROS"
		);
		super.console.newLine();
	}
	
	@Override
	protected ResponseWhile bodyScreen() {
		while(true) {
			ResponseWhile res;
			
			res = this.selectDifficulty();
			if(res.repeat()) continue;
			else if(res.exit()) break;
			
			res = this.selectQuantityRandomNumbers();
			if(res.repeat()) continue;
			else if(res.exit()) break;
			
			this.exercise.setQuantityNumbers(this.selectedQuantityRandomNumbers);
			this.exercise.setDifficulty(this.selectedDifficulty);
			this.exercise.execute();
			
			break;
		}
		
		return ResponseToWhile.exit();
	}
	
	protected final void showDifficults() {
		if(!this.difficults.isEmpty()) {
			this.difficults.forEach((k,d) -> {
				this.console.message(k + " - " + d.getName());
			});
		}
		this.console.message("0 - Sair/Voltar");
	}
	
	protected ResponseWhile selectDifficulty() {
		this.showDifficults();
		
		Optional<Integer> userEntry = this.console.inputInteger("Escolha");
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}	
		int selected = userEntry.get();
		if(selected == 0) {
			return ResponseToWhile.exit();
		}
		
		if(!this.difficults.containsKey(selected)) {
			this.console.alert("Escolha '" + selected + "' inválida. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		this.selectedDifficulty = this.difficults.get(selected);
		
		return ResponseToWhile.nextIteration();
	}
	
	protected ResponseWhile selectQuantityRandomNumbers() {
		Optional<Integer> userEntry = this.console.inputInteger("Q. de Números");
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}
		int selected = userEntry.get();
		if(selected == 0) {
			return ResponseToWhile.exit();
		}
		if(selected < 2 || selected >= 11) {
			this.console.alert("Quantidade de números inválida.");
			return ResponseToWhile.repeat();
		}
		
		this.selectedQuantityRandomNumbers = selected;
		return ResponseToWhile.nextIteration();
	}
	
	private void validateDifficults(Map<Integer, DifficultyExercise> difficults) {
		if(!difficults.isEmpty()) return;
		throw new IllegalArgumentException(
			"Dificuldades não definidas para exercício matemático."
		);
	}
}
