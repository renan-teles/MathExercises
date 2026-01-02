package com.mathexercises.domain.exercises.difficults;

public class Normal extends AbstractDifficulty{
	public Normal() {
		super("NORMAL", 5);
	}

	@Override
	public int getRandomNumber() {
		return super.random.nextInt(10,99);
	}
}
