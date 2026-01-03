package com.mathexercises.dto.responseexerciselogic;

import java.util.Optional;

public abstract class ResponseToExerciseLogic {
	public static <T> ResponseExerciseLogic<T> repeatRound() {
		return new ResponseExerciseLogic<>(Optional.empty(), false, true);
	}
	
	public static <T> ResponseExerciseLogic<T> exitExercise() {
		return new ResponseExerciseLogic<>(Optional.empty(), true, false);
	}
	
	public static <T> ResponseExerciseLogic<T> nextRound(T value){
		return new ResponseExerciseLogic<T>(Optional.of(value), false, false);
	}
	
	public static ResponseExerciseLogic<Void> nextRound(){
		return new ResponseExerciseLogic<>(Optional.empty(), false, false);
	}
}
