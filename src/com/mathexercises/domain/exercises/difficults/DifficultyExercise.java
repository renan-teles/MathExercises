package com.mathexercises.domain.exercises.difficults;

import java.util.Random;

import com.mathexercises.singleton.RandomSingleton;

public abstract class DifficultyExercise {
	protected Random random;
	protected String name;
	protected int iterationsNumber;

	public DifficultyExercise( 
		String name, 
		int iterationsNumber
	) {
		super();
		this.random = RandomSingleton.inject();
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
