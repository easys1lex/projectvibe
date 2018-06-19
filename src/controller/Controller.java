package controller;

import application.Main;

public abstract class Controller {
	protected static Main main;
	
	public static void setMain(Main m){
		main = m;
	}
	public static Main getMain() {
		return main;
	}
	
}
