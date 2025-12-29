package com.mathexercises.domain.exercises.basicmathoperations;

import java.util.Random;

import com.mathexercises.domain.exercises.DifficultyExercise;

public class HardBasicMathOperation extends DifficultyExercise{
	public HardBasicMathOperation(Random random) {
		super(random, "DIFÍCIL", 10);
	}

	@Override
	public int getRandomNumber() {
		return super.random.nextInt(100,1000);
	}
}
