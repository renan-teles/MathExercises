package com.mathexercises.singleton;

import java.util.Random;

public abstract class RandomSingleton {
	private static Random instance = null;
	
	public static Random inject(){
		if(instance == null) {
			instance = new Random();
		}
		return instance;
	}
}
