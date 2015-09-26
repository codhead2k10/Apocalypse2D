package com.main;

public class Main {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Easy Engine";
	
	public static void main(String[] args){
		Window w = new Window(WIDTH, HEIGHT, TITLE);
		w.build();
		w.initScene();
		w.loop();
		w.destroy();
	}

}
