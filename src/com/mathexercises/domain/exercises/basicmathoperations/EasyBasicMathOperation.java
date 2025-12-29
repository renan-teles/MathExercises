package com.mathexercises.domain.exercises.basicmathoperations;

import java.util.Random;

import com.mathexercises.domain.exercises.DifficultyExercise;

public class EasyBasicMathOperation extends DifficultyExercise{
	public EasyBasicMathOperation(Random random) {
		super(random, "FÁCIL", 5);
	}

	@Override
	public int getRandomNumber() {
		return super.random.nextInt(1,9);
	}
}
