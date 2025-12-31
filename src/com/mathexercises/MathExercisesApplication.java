package com.mathexercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mathexercises.domain.exercises.basicmathoperations.*;
import com.mathexercises.domain.exercises.difficults.*;
import com.mathexercises.domain.exercises.mathexpressionwithunknownnumber.*;
import com.mathexercises.view.screens.*;
import com.mathexercises.domain.exercises.*;

public class MathExercisesApplication {
	public static void main(String[] args) {
		Map<Integer, DifficultyExercise> difficults = null;
		Map<Integer, Screen> childrenScreens = null;
		
		
		//MATH EXERCISES
		//Basic Math Operations
		MathExercise sum = new SumOfNumbers();
		MathExercise subtraction = new SubtractionOfNumbers();
		MathExercise multiplication = new MultiplicationOfNumbers();
		
		//Math Expression With Unknown Number
		List<MathOperation> operations = new ArrayList<>();
		operations.add(new SumMathOperation());
		operations.add(new MultiplicationMathOperation());
		operations.add(new SubtractionMathOperation());
		MathExercise expressionWithUnknownNumber = new MathExpressionWithUnknownNumber(
			operations
		);
		
		
		//DIFFICULTS
		DifficultyExercise easy = new Easy();
		DifficultyExercise normal = new Normal();
		DifficultyExercise hard = new Hard();
		difficults = new HashMap<>();
		difficults.put(1, easy);
		difficults.put(2, normal);
		difficults.put(3, hard);
		
		
		//EXERCISE SCREENS
		//Basic Math Operations
		Screen sumScreen = new ExerciseScreen(sum, difficults); 
		Screen subtractionScreen = new ExerciseScreen(subtraction, difficults); 
		Screen multiplicationScreen = new ExerciseScreen(multiplication, difficults); 
	
		//Math Expression With Unknown Number
		Screen expressionWithUnknownNumberScreen = new ExerciseScreen(
			expressionWithUnknownNumber, difficults
		);
		
		
		//MAIN SCREENS
		//Select Math Exercise
		childrenScreens = new HashMap<>();
		childrenScreens.put(1, sumScreen);
		childrenScreens.put(2, subtractionScreen);
		childrenScreens.put(3, multiplicationScreen);
		childrenScreens.put(4, expressionWithUnknownNumberScreen);
		Screen selectMathExerciseScreen = new MainScreen(
			"ESCOLHER EXERCÍCIO MATEMÁTICO", childrenScreens	
		);
		
		//Start Screen
		childrenScreens = new HashMap<>();
		childrenScreens.put(1, selectMathExerciseScreen);
		Screen startScreen = new MainScreen(
			"EXERCÍCIOS MATEMÁTICOS", childrenScreens		
		);
		
	
		//START
		startScreen.render();
	}
}
