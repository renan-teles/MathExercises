package com.mathexercises.domain.exercises.basicmathoperations;

import java.util.Random;

import com.mathexercises.domain.exercises.DifficultyExercise;

public class NormalBasicMathOperation extends DifficultyExercise{
	public NormalBasicMathOperation(Random random) {
		super(random, "NORMAL", 5);
	}

	@Override
	public int getRandomNumber() {
		return super.random.nextInt(10,99);
	}
}
