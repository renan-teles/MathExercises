package com.mathexercises.service;

import java.util.Optional;
import java.util.Scanner;

import com.mathexercises.utils.NumberConverter;

public class ConsoleService {
	 private Scanner scanner;
	 private final int standartWidth = 50;

	 public ConsoleService(Scanner scanner) {
		 this.scanner = scanner;
	 }

	 public void newLine() { System.out.println(); }
	    
	 public void line() {
	     System.out.println("-".repeat(this.standartWidth));
	 }

	 public void title(String msg) {
		 newLine();
		 line();
	     showCentered(msg.toUpperCase(), this.standartWidth);
	     line();
	}
	    
	public void topic(String msg) {
		System.out.println("# " + msg);
	}
	
	public void subtopic(String msg) {
		System.out.println("- " + msg);
	}
	    
	public void message(String msg) {
		System.out.println(msg);
	}

	public void alert(String msg) {
	    this.newLine();
	    System.out.println("\u001B[33m[ALERTA] " + msg + "\u001B[0m");
	    this.newLine();
	}

	public void unsuccess(String msg) {
		this.newLine();
	    System.out.println("\u001B[31m[ERRO] " + msg + "\u001B[0m");
	    this.newLine();
	}

	public void success(String msg) {
		this.newLine();
	    System.out.println("\u001B[32m[SUCESSO] " + msg + "\u001B[0m");
	    this.newLine();
	}
	    
	public void info(String msg) {
	    System.out.println("\u001B[36m[INFO] " + msg + "\u001B[0m");
	}

	public Optional<String> inputString(String msg) {
	    System.out.print(msg + ": ");
	    String entry = scanner.nextLine().trim();
	    this.newLine();
	        
	    if (entry.isEmpty()) {
	        return Optional.empty();
	    }
	    return Optional.of(entry);
	}

	public Optional<String> inputString() {
		return this.inputString("Digite o texto");
	}

	public Optional<Integer> inputInteger(String msg) {
		System.out.print(msg + ": ");
	    String entry = scanner.nextLine().trim();
	    this.newLine();
	        
	    Optional<Integer> op = NumberConverter.parseStringToInt(entry);
	    if(op.isEmpty()) {
	    	this.alert("Valor inválido! Não é um número inteiro.");
	    }
	        
	    return op;
	}

	public Optional<Integer> inputInteger() {
	    return this.inputInteger("Digite um número inteiro");
	}

	public Optional<Float> inputFloat(String msg) {
	    System.out.print(msg + ": ");
	    String entry = scanner.nextLine().trim();
	    this.newLine();
	        
	    Optional<Float> op = NumberConverter.parseStringToFloat(entry);
	    if(op.isEmpty()) {
	    	this.alert("Valor inválido! Não é um decimal válido.");
	    }
	       
	    return op;
	}

	public Optional<Float> inputFloat() {
	    return this.inputFloat("Digite um número decimal");
	}

	public Optional<Boolean> inputConfirm(String msg) {
	    System.out.print(msg + " (s/n): ");
	    String entry = scanner.nextLine().trim().toLowerCase();
	    this.newLine();
	        
	    if (entry.equals("s")) {
	    	return Optional.of(true);
	    }
	    else if (entry.equals("n")) {
	    	return Optional.of(false);
	    }

	    this.alert("Entrada inválida! Use apenas S ou N.");
	    return Optional.empty();
	}
	    
	private void showCentered(String msg, int width) {
	    msg = msg.trim();
	    int spaces = (width - msg.length()) / 2;
	    spaces = Math.max(0, spaces);

	    System.out.println(" ".repeat(spaces) + msg);
	}
}
