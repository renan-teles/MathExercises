package com.mathexercises.singleton;

import java.util.Scanner;

import com.mathexercises.service.ConsoleService;

public abstract class ConsoleServiceSingleton {
	private static ConsoleService instance = null;
	
	public static ConsoleService inject(){
		if(instance == null) {
			instance = new ConsoleService(new Scanner(System.in));
		}
		return instance;
	}
}
