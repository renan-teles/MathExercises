package com.mathexercises.domain.exercises;

import java.math.BigDecimal;
import java.util.Optional;

import com.mathexercises.domain.exercises.difficults.Difficulty;
import com.mathexercises.domain.math.MathExpression;
import com.mathexercises.dto.responseexerciselogic.ResponseExerciseLogic;
import com.mathexercises.dto.responseexerciselogic.ResponseToExerciseLogic;
import com.mathexercises.dto.responsewhile.ResponseToWhile;
import com.mathexercises.dto.responsewhile.ResponseWhile;
import com.mathexercises.exceptions.MathExerciseException;
import com.mathexercises.service.ConsoleService;
import com.mathexercises.singleton.ConsoleServiceSingleton;
import com.mathexercises.utils.NumberConverter;

public abstract class AbstractMathExercise implements MathExercise{
	protected final ConsoleService console = ConsoleServiceSingleton.inject();
	protected final MathExpression expression = new MathExpression();
	
	protected final String EXIT = "exit";
	protected final String questionForUser;
	protected final String name;
	protected final String description;
	
	protected Difficulty difficulty = null;
	
	public AbstractMathExercise(
		String name, 
		String description,
		String messageForUser
	) {
		super();
		this.name = name.toUpperCase();
		this.description = this.capitalize(description);
		this.questionForUser = messageForUser;
	}
	
	@Override
	public void setQuantityNumbers(int quantity) {
		this.expression.setQuantityNumbers(quantity);
	}

	@Override
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public final void execute() {
		this.validateDifficulty();
		this.showHeader();
		
		int n = 1;
		int iterations = this.difficulty.getIterationsNumber();
		
		while(n <= iterations) {
			this.console.line();
			this.console.topic(n + " de " + iterations);
			this.console.newLine();
			
			ResponseWhile res = this.exerciseLogic();
			if(res.breakLoop()) break;
			else if(res.repeatCurrentIteration()) continue;
			
			n++;
		}
	}
	
	protected void showHeader() {
		this.console.title(this.name);
		this.console.message(this.description);
		this.console.message("Dificuldade: " + this.difficulty.getName());
		this.console.newLine();
		this.console.topic(
			new StringBuilder()
				.append("Digite '")
				.append(this.EXIT)
				.append("' para sair.")
				.toString()
		);
		this.console.newLine();
	}
	
	protected final ResponseWhile exerciseLogic() {
		this.defineExpression();
		
		//Get User Entry
		ResponseExerciseLogic<String> userEntry = this.getUserEntry();
		if(userEntry.repeatRound()) {
			return ResponseToWhile.repeatCurrentIteration();
		}
		else if(userEntry.exitExercise()) {
			return ResponseToWhile.exitLoop();			
		}
		
		//Parse To BigDecimal
		ResponseExerciseLogic<BigDecimal> parsedEntry = this.parseUserEntry(
			userEntry.value().get()
		);
		if(parsedEntry.repeatRound()) {
			return ResponseToWhile.repeatCurrentIteration();
		}
		else if(parsedEntry.exitExercise()) {
			return ResponseToWhile.exitLoop();
		}
		
		//Check If Is Correct
		ResponseExerciseLogic<Void> finalResponse = this.checkIsCorrect(
			parsedEntry.value().get()
		);
		if(finalResponse.repeatRound()) {
			return ResponseToWhile.repeatCurrentIteration();	
		}
		else if(finalResponse.exitExercise()) {
			return ResponseToWhile.exitLoop();
		}
		
		return ResponseToWhile.nextIteration();
	}
	
	protected abstract void defineExpression();
	protected abstract ResponseExerciseLogic<Void> checkIsCorrect(BigDecimal value);
	
	protected ResponseExerciseLogic<String> getUserEntry(){
		Optional<String> userEntry = this.console.inputString(
			this.questionForUser
		);
		
		if(userEntry.isEmpty()) {
			return ResponseToExerciseLogic.repeatRound();
		}
		else if(userEntry.get().equalsIgnoreCase(this.EXIT)) {
			this.expression.clearExpression();
			return ResponseToExerciseLogic.exitExercise();
		}
		return ResponseToExerciseLogic.nextRound(userEntry.get());
	}
	
	protected ResponseExerciseLogic<BigDecimal> parseUserEntry(String value){
		Optional<BigDecimal> optRes 
			= NumberConverter.parseStringToBigDecimal(value);
		
		if(optRes.isEmpty()) {
			this.console.alert("Valor inválido. Tente novamente.");
			return ResponseToExerciseLogic.repeatRound();
		}
		return ResponseToExerciseLogic.nextRound(optRes.get());
	}
	
	private void validateDifficulty() {
		if(this.difficulty != null) return;
		throw new MathExerciseException(
			new StringBuilder()
				.append("Dificuldade não selecionada para exercício: ")
				.append(this.name)
				.toString()
		);
	}
	
	private String capitalize(String s) {
		return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
	}
}
