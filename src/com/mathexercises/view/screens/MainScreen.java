package com.mathexercises.view.screens;

import java.util.Map;
import java.util.Optional;

import com.mathexercises.dto.responsewhile.*;
import com.mathexercises.service.ConsoleService;
import com.mathexercises.singleton.ConsoleServiceSingleton;

public class MainScreen implements Screen{
	protected String title;	
	protected ConsoleService console;

	private Map<Integer, Screen> childrens;

	public MainScreen(
		String title, 
		Map<Integer, Screen> childrens
	) {
		super();
		this.title = title.toUpperCase();
		this.childrens = childrens;
		this.console = ConsoleServiceSingleton.inject();
	}
	
	protected MainScreen(String title) {
		super();
		this.title = title;
		this.console = ConsoleServiceSingleton.inject();
	}

	@Override
	public final void render() {
		while(true) {
			this.showHeader();
			
			ResponseWhile res = this.bodyScreen();
			if(res.repeatCurrentIteration()) continue;
			else if(res.breakLoop()) break;
		}
	}
		
	protected void showHeader() {
		this.console.title(this.title);
		
		if(!this.childrens.isEmpty()) {
			this.childrens.forEach((k,s) -> {
				this.console.message(
					new StringBuilder()
						.append(k)
						.append(" - ")
						.append(s.getTitle())
						.toString()
				);
			});
		}
		this.console.message("0 - Sair/Voltar");
	}
	
	protected ResponseWhile bodyScreen() {
		Optional<Integer> userEntry = this.console.inputInteger(
			"Escolha"
		);
		if(userEntry.isEmpty()) {
			return ResponseToWhile.repeatCurrentIteration();
		}	
	
		int selected = userEntry.get();
		if(selected == 0) {
			return ResponseToWhile.exitLoop();
		}
		
		if(!this.childrens.containsKey(selected)) {
			this.console.alert(
				new StringBuilder()
					.append("Escolha '")
					.append(selected)
					.append("' inválida. Tente novamente.")
					.toString()
			);
			return ResponseToWhile.repeatCurrentIteration();
		}
		this.childrens.get(selected).render();
		
		return ResponseToWhile.nextIteration();
	}
	
	@Override
	public final String getTitle() {
		return this.title;
	}
}
