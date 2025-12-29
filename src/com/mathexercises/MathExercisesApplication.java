package com.mathexercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.mathexercises.domain.exercises.basicmathoperations.*;
import com.mathexercises.service.ConsoleService;
import com.mathexercises.view.screens.*;
import com.mathexercises.domain.exercises.*;

public class MathExercisesApplication {
	public static void main(String[] args) {
		//GENERAL USE
		Random random = new Random();
		ConsoleService console = new ConsoleService(new Scanner(System.in));
		Map<Integer, DifficultyExercise> difficults = null;
		Map<Integer, Screen> childrenScreens = null;
		
		
		//MATH EXERCISES
		MathExercise sum = new SumOfNumbers(console);
		MathExercise subtraction = new SubtractionOfNumbers(console);
		MathExercise multiplication = new MultiplicationOfNumbers(console);
		
		
		//DIFFICULTS - MATH EXERCISES
		//Basic Math Operations
		DifficultyExercise easyBasicMathOperation = new EasyBasicMathOperation(random);
		DifficultyExercise normalBasicMathOperation = new NormalBasicMathOperation(random);
		DifficultyExercise hardBasicMathOperation = new HardBasicMathOperation(random);
		
		
		//EXERCISE SCREENS
		//Basic Math Operations
		difficults = new HashMap<>();
		difficults.put(1, easyBasicMathOperation);
		difficults.put(2, normalBasicMathOperation);
		difficults.put(3, hardBasicMathOperation);
		Screen sumScreen = new BasicOperationsScreen(console, sum, difficults); 
		Screen subtractionScreen = new BasicOperationsScreen(console, subtraction, difficults); 
		Screen multiplicationScreen = new BasicOperationsScreen(console, multiplication, difficults); 
	
		
		//MAIN SCREENS
		//Select Math Exercise
		childrenScreens = new HashMap<>();
		childrenScreens.put(1, sumScreen);
		childrenScreens.put(2, subtractionScreen);
		childrenScreens.put(3, multiplicationScreen);
		Screen selectMathExerciseScreen = new MainScreen(
			"ESCOLHER EXERCÍCIO MATEMÁTICO", childrenScreens, console		
		);
		
		//Start Screen
		childrenScreens = new HashMap<>();
		childrenScreens.put(1, selectMathExerciseScreen);
		Screen startScreen = new MainScreen(
			"EXERCÍCIOS MATEMÁTICOS", childrenScreens, console		
		);
		
	
		//START
		startScreen.render();
	}
}
