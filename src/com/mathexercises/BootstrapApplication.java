package com.mathexercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mathexercises.domain.exercises.basicmathoperations.*;
import com.mathexercises.domain.exercises.difficults.*;
import com.mathexercises.domain.exercises.*;
import com.mathexercises.domain.exercises.averageofnumbers.*;
import com.mathexercises.domain.exercises.mathexpressionwithunknownnumber.*;
import com.mathexercises.domain.math.operations.*;
import com.mathexercises.view.screens.*;

public class BootstrapApplication {
	public static void run() {
		Map<Integer, Difficulty> normalDifficults = null, difficultsWithQuantityNumbers = null;
		Map<Integer, Screen> childrenScreens = null;
		
		
		//MATH EXERCISES
		//Basic Math Operations
		MathExercise sum = new SumOfNumbers();
		MathExercise subtraction = new SubtractionOfNumbers();
		MathExercise multiplication = new MultiplicationOfNumbers();
		MathExercise division = new DivisionOfNumbers();
	
		//Math Expression With Unknown Number
		List<MathOperation> operations = new ArrayList<>();
		operations.add(new SumMathOperation());
		operations.add(new SubtractionMathOperation());
		operations.add(new MultiplicationMathOperation());
		MathExercise expressionWithUnknownNumber = new MathExpressionWithUnknownNumber(
			operations
		);
		
		//Average Of Numbers
		MathExercise avg = new AverageOfNumbers();
		
		
		//DIFFICULTS
		Difficulty easy = new Easy();
		Difficulty normal = new Normal();
		Difficulty hard = new Hard();
		normalDifficults = new HashMap<>();
		normalDifficults.put(1, easy);
		normalDifficults.put(2, normal);
		normalDifficults.put(3, hard);
		
		
		//EXERCISE SCREENS
		//Basic Math Operations
		Screen sumScreen = new ExerciseWithSelectQuantityNumbersScreen(
			sum, normalDifficults
		); 
		Screen subtractionScreen = new ExerciseWithSelectQuantityNumbersScreen(
			subtraction, normalDifficults
		); 
		Screen multiplicationScreen = new ExerciseWithSelectQuantityNumbersScreen(
			multiplication, normalDifficults
		); 
		Screen divisionScreen = new ExerciseScreen(
			division, normalDifficults
		); 
		
		//Math Expression With Unknown Number
		difficultsWithQuantityNumbers = new HashMap<>();
		difficultsWithQuantityNumbers.put(1, new DifficultyWithQuantityNumbers(easy, 2));
		difficultsWithQuantityNumbers.put(2, new DifficultyWithQuantityNumbers(normal, 3));
		difficultsWithQuantityNumbers.put(3, new DifficultyWithQuantityNumbers(hard, 3));
		Screen expressionWithUnknownNumberScreen = new ExerciseScreen(
			expressionWithUnknownNumber, difficultsWithQuantityNumbers
		);
		
		//Average Of Numbers
		Screen avgScreen = new ExerciseWithSelectQuantityNumbersScreen(
			avg, normalDifficults
		); 
		
		
		//MAIN SCREENS
		//Select Math Exercise
		childrenScreens = new HashMap<>();
		childrenScreens.put(1, sumScreen);
		childrenScreens.put(2, subtractionScreen);
		childrenScreens.put(3, multiplicationScreen);
		childrenScreens.put(4, divisionScreen);
		childrenScreens.put(5, expressionWithUnknownNumberScreen);
		childrenScreens.put(6, avgScreen);
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
