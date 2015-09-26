package com.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


public class Mesh {
	
	private int vao, vbo, ibo, indicesCount;
	
	public Mesh(Vertex[] vertices, int[] indices){
		indicesCount = indices.length;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Utils.toFloatBuffer(vertices),GL_STATIC_DRAW);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.toIntBuffer(indices),GL_STATIC_DRAW);
	}
	
	public void draw(){
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo); 
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 20, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 20, 12);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glDrawElements(GL_TRIANGLES, indicesCount, GL_UNSIGNED_INT,  0);
		
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
	}
	
	

}