package com.mathexercises.domain.exercises.difficults;

import java.util.Random;

import com.mathexercises.singleton.RandomSingleton;

public abstract class AbstractDifficulty implements Difficulty{
	protected final Random random = RandomSingleton.inject();
	
	protected String name;
	protected int iterationsNumber;

	public AbstractDifficulty( 
		String name, 
		int iterationsNumber
	) {
		super();
		this.name = name;
		this.iterationsNumber = iterationsNumber;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getIterationsNumber() {
		return this.iterationsNumber;
	}
}       
