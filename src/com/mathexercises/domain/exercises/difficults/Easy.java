package com.mathexercises.domain.exercises.difficults;

public class Easy extends AbstractDifficulty{
	public Easy() {
		super("FÁCIL", 5);
	}

	@Override
	public int getRandomNumber() {
		return super.random.nextInt(1,9);
	}
}
