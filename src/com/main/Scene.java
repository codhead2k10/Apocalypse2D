package com.main;

import com.maths.Matrix4f;
import com.maths.Vector3f;
import com.rendering.Mesh;
import com.rendering.Shader;
import com.rendering.Texture;
import com.rendering.Utils;
import com.rendering.Vertex;

public class Scene {
	
	private Mesh mesh;
	private Texture texture;
	private Shader shader;
	private Vertex[] vertices;
	private int[] indices;
	
	private Vector3f translationVector = new Vector3f(0, 0, 0);
	private Vector3f rotationVector = new Vector3f(0, 0, 0);
	private Vector3f scaleVector = new Vector3f(1, 1, 1);
	
	public Scene(){
		
		vertices = new Vertex[] { new Vertex(-0.5f,-0.5f,0, 0, 0),new Vertex(0.5f,-0.5f,0,1,0),
		                          new Vertex(-0.5f,-0.5f,1, 0, 0),new Vertex(0.5f,-0.5f,1, 0, 0),
		                          new Vertex(-0.5f, 0.5f,0, 0, 1),new Vertex(0.5f, 0.5f,0,1,1),
		                          new Vertex(-0.5f, 0.5f,1, 0, 0),new Vertex(0.5f, 0.5f,1, 0, 0)
		                          
				
		};
	
		
		indices = new int[] {0, 1, 2, 1, 2, 3, 2, 3, 6, 3, 6, 7, 3, 7, 5, 1, 3, 5, 0, 2, 4, 2, 4, 6, 4, 5, 6, 5, 6, 7, 0, 1, 4, 4, 5, 1};
	//	indices = new int[] {0,1,5,1,5,6};
		
		mesh  = new Mesh(vertices, indices);
		texture = Utils.loadTexture("grass1.png", "PNG");
		shader = new Shader("test.vs","test.fs");
	}
	
	public void render(){
		shader.bind();
		texture.bind();
		mesh.draw();
	}
	
	public void update(){
		translationVector.z = 3;
		rotationVector.x = 1;
		Matrix4f translation = new Matrix4f().translation(translationVector);
		Matrix4f rotation = new Matrix4f().rotation(rotationVector);
		Matrix4f scale = new Matrix4f().scale(scaleVector);
		Matrix4f projection = new Matrix4f().projection(70f, Window.getWidth(), Window.getHeight(), 0.0001f, 1000);
		shader.setUniformMat4f("transformation", projection.mul(scale.mul(rotation.mul(translation))));
		shader.unbind();
	}

}
