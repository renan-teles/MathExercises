package com.mathexercises.view.screens;

import java.util.Map;
import java.util.Optional;

import com.mathexercises.domain.exercises.*;
import com.mathexercises.domain.exercises.difficults.Difficulty;
import com.mathexercises.dto.responsewhile.*;

public class ExerciseScreen extends MainScreen{
	protected final MathExercise exercise;
	protected final Map<Integer, Difficulty> difficults;
	protected Difficulty difficulty;
	
	public ExerciseScreen(
		MathExercise exercise, 
		Map<Integer, Difficulty> difficults
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
			"Selecione a DIFICULDADE"
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
			
			this.exercise.setQuantityNumbers(
				this.difficulty.getQuantityNumbers()
			);
			this.exercise.setDifficulty(this.difficulty);
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
	
	protected final ResponseWhile selectDifficulty() {
		this.showDifficults();
		
		Optional<Integer> userEntry = this.console.inputInteger(
			"Escolha"
		);
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}	
		int selected = userEntry.get();
		if(selected == 0) {
			return ResponseToWhile.exit();
		}
		
		if(!this.difficults.containsKey(selected)) {
			this.console.alert(
				new StringBuilder()
					.append("Escolha '")
					.append(selected)
					.append("' inválida. Tente novamente.")
					.toString()
			);
			return ResponseToWhile.repeat();
		}
		this.difficulty = this.difficults.get(selected);
		
		return ResponseToWhile.nextIteration();
	}
	
	protected final void validateDifficults(Map<Integer, Difficulty> difficults) {
		if(!difficults.isEmpty()) return;
		throw new IllegalArgumentException(
			"Dificuldades não definidas para exercício matemático."
		);
	}
}
