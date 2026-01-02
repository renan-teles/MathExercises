package com.mathexercises.domain.exercises;

import com.mathexercises.domain.exercises.difficults.Difficulty;

public interface MathExercise {
	public void execute();
	public void setQuantityNumbers(int quantity);
	public void setDifficulty(Difficulty difficulty);
	public String getName();
}
