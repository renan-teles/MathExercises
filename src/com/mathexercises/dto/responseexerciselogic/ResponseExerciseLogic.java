package com.mathexercises.dto.responseexerciselogic;

import java.util.Optional;

public record ResponseExerciseLogic<T>(
	Optional<T> value,
	boolean exitExercise,
	boolean repeatRound
) {}
