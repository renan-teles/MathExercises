package com.mathexercises.domain.exercises.difficults;

public interface Difficulty {
	public default int getQuantityNumbers() { return 2; }
	public int getRandomNumber();
	public String getName();
	public int getIterationsNumber();
}
