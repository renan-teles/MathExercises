package com.mathexercises.view.screens;

import com.mathexercises.domain.exercises.basicmathoperations.BasicMathOperation;
import com.mathexercises.dto.responsewhile.ResponseToWhile;
import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.service.ConsoleService;

import java.util.Map;
import java.util.Optional;

import com.mathexercises.domain.exercises.DifficultyExercise;
import com.mathexercises.domain.exercises.MathExercise;

public class BasicOperationsScreen extends ExerciseScreen{
	private DifficultyExercise selectedDifficulty;
	private int selectedQuantityRandomNumbers;
	
	public BasicOperationsScreen(
		ConsoleService console, 
		MathExercise exercise,
		Map<Integer, DifficultyExercise> difficults
	) {
		super(console, exercise, difficults);
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
			
			BasicMathOperation mathExercise = (BasicMathOperation) this.exercise;
			mathExercise.setRandomQuantityNumbers(this.selectedQuantityRandomNumbers);
			mathExercise.setDifficulty(this.selectedDifficulty);
			mathExercise.execute();
			
			break;
		}
		
		return ResponseToWhile.exit();
	}
	
	private ResponseWhile selectDifficulty() {
		super.showDifficults();
		
		Optional<Integer> userEntry = this.console.inputInteger("Escolha");
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}	
		int selected = userEntry.get();
		if(selected == 0) {
			return ResponseToWhile.exit();
		}
		
		if(!super.difficults.containsKey(selected)) {
			this.console.alert("Escolha '" + selected + "' inválida. Tente novamente.");
			return ResponseToWhile.repeat();
		}
		this.selectedDifficulty = this.difficults.get(selected);
		
		return ResponseToWhile.nextIteration();
	}
	
	private ResponseWhile selectQuantityRandomNumbers() {
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
}
