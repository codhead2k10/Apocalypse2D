package com.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.maths.Matrix4f;
import com.maths.Vector3f;

public class Shader {

	public final int TYPE_VERTEX = GL_VERTEX_SHADER;
	public final int TYPE_FRAGMENT = GL_FRAGMENT_SHADER;

	private int program;

	public Shader(String shaderSource, int shaderType) {
		program = glCreateProgram();

		if (shaderType == TYPE_VERTEX) {
			addShader(shaderSource + ".vs", TYPE_VERTEX);
		} else if (shaderType == TYPE_FRAGMENT) {
			addShader(shaderSource + ".fs", TYPE_FRAGMENT);
		} else {
			System.err.println("Invalid shader type passed into shader constructor");
		}
		compileShader();
	}

	public Shader(String vertex, String fragment) {
		program = glCreateProgram();

		if (program == GL_FALSE) {
			System.err.println("Shader creation failed in constructor");
			System.exit(1);
		}
		addShader(vertex, TYPE_VERTEX);
		addShader(fragment, TYPE_FRAGMENT);
		compileShader();
	}

	private void addShader(String text, int type) {
		int shader = glCreateShader(type);

		if (shader == -1) {
			System.err.println("Shader creation failed");
			System.exit(1);
		}

		String shaderText = Utils.loadShader(text);

		glShaderSource(shader, shaderText);
		glCompileShader(shader);

		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}

		glAttachShader(program, shader);
	}

	public void compileShader() {
		glLinkProgram(program);

		if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println(glGetProgramInfoLog(program, 1024));
			System.err.println("Error in Linking program!");
			System.exit(1);
		}

		glValidateProgram(program);

		if (glGetProgrami(program, GL_VALIDATE_STATUS) == GL_FALSE) {
			System.err.println(glGetProgramInfoLog(program, 1024));
			System.err.println("Error in validating program");
			System.exit(1);
		}
	}

	public void bind() {
		glUseProgram(program);
	}

	public void unbind() {
		glUseProgram(0);
	}

	public int getUniform(String name) {

		int result = glGetUniformLocation(program, name);
		if (result == -1)
			System.err.println("Could not find uniform variable '" + name
					+ "'!");
		return result;
	}

	public void setUniform1i(String name, int value) {
		glUniform1i(getUniform(name), value);
	}

	public void setUniform1f(String name, float value) {
		glUniform1f(getUniform(name), value);
	}

	public void setUniform2f(String name, float x, float y) {
		glUniform2f(getUniform(name), x, y);
	}

	public void setUniform3f(String name, Vector3f vector) {
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}

	public void setUniformMat4f(String name, Matrix4f matrix) {
		glUniformMatrix4(getUniform(name), true, Utils.toFloatBuffer(matrix));
	}

}
