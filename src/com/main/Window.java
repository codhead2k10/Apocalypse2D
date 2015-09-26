package com.main;


import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	public int width, height;
	public String title;
	
	private Scene scene;
	
	public Window(int width, int height, String title){
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	public void build(){
		try {
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.err.println("Failed to construct Window!");
			System.exit(1);
		}
	}
	
	public void loop(){
		while(!Display.isCloseRequested()){
			update();
			render();
		}
	}
	
	public void destroy(){
		Display.destroy();
	}
	
	private void update(){
		Display.sync(60);
		Display.update();
		
		scene.update();
	}
	
	private void render(){
		glEnable(GL_DEPTH_TEST);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		scene.render();
	}
	
	public void initScene(){
		scene = new Scene();
	}
	

	public static int getWidth() {
		return Display.getWidth();
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public static int getHeight() {
		return Display.getHeight();
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
