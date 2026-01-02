package com.mathexercises.domain.exercises.difficults;

public class Hard extends AbstractDifficulty{
	public Hard() {
		super("DIFÍCIL", 10);
	}

	@Override
	public int getRandomNumber() {
		return super.random.nextInt(100,1000);
	}
}
