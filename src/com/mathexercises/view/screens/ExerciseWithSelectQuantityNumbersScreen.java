package com.mathexercises.view.screens;

import java.util.Map;
import java.util.Optional;

import com.mathexercises.domain.exercises.*;
import com.mathexercises.domain.exercises.difficults.*;
import com.mathexercises.dto.responsewhile.*;

public class ExerciseWithSelectQuantityNumbersScreen extends ExerciseScreen{
	private int quantityNumbers;
	
	public ExerciseWithSelectQuantityNumbersScreen(
		MathExercise exercise, 
		Map<Integer, Difficulty> difficults
	) {
		super(exercise, difficults);
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
			
			res = this.selectQuantityNumbers();
			if(res.repeat()) continue;
			else if(res.exit()) break;
			
			this.exercise.setQuantityNumbers(this.quantityNumbers);
			this.exercise.setDifficulty(this.difficulty);
			this.exercise.execute();
			
			break;
		}
		
		return ResponseToWhile.exit();
	}
	
	protected ResponseWhile selectQuantityNumbers() {
		Optional<Integer> userEntry = this.console.inputInteger("Q. de Números");
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeat();
		}
		
		int selected = userEntry.get();
		if(selected == 0) {
			return ResponseToWhile.exit();
		}
		else if(selected < 2 || selected >= 11) {
			this.console.alert("Quantidade de números inválida.");
			return ResponseToWhile.repeat();
		}
		
		this.quantityNumbers = selected;
		return ResponseToWhile.nextIteration();
	}
}
