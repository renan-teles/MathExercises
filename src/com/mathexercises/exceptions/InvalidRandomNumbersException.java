package com.mathexercises.exceptions;

public class InvalidRandomNumbersException extends RuntimeException{
	public InvalidRandomNumbersException() {
		super(
			"Números aleatórios não definidos corretamente"
		);
	}
}
