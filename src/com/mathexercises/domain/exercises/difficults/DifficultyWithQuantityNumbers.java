package com.mathexercises.domain.exercises.difficults;

public class DifficultyWithQuantityNumbers implements Difficulty{
	private int quantityNumbers;
	private final Difficulty difficulty;
	
	public DifficultyWithQuantityNumbers(
			Difficulty difficulty,
			int quantityNumbers
	) {
		this.difficulty = difficulty;
		this.quantityNumbers = quantityNumbers;
	}
	
	@Override
	public int getRandomNumber() {
		return this.difficulty.getRandomNumber();
	}

	@Override
	public String getName() {
		return this.difficulty.getName();
	}

	@Override
	public int getIterationsNumber() {
		return this.difficulty.getIterationsNumber();
	}
	
	@Override
	public int getQuantityNumbers() {
		return this.quantityNumbers;
	}
}
