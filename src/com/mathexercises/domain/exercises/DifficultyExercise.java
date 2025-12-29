package com.mathexercises.domain.exercises;

import java.util.Random;

public abstract class DifficultyExercise {
	protected Random random;
	protected String name;
	protected int iterationsNumber;
	
	public DifficultyExercise(
		Random random, 
		String name, 
		int iterationsNumber
	) {
		super();
		this.random = random;
		this.name = name;
		this.iterationsNumber = iterationsNumber;
	}

	public abstract int getRandomNumber();

	public String getName() {
		return name;
	}

	public int getIterationsNumber() {
		return iterationsNumber;
	}
}
