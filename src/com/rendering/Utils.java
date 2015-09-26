package com.rendering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.TextureLoader;

import com.maths.Matrix4f;

public class Utils {

	public static FloatBuffer toFloatBuffer(Vertex[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length * 5);

		for (int i = 0; i < data.length; i++) {
			buffer.put(data[i].getX());
			buffer.put(data[i].getY());
			buffer.put(data[i].getZ());
			buffer.put(data[i].getTx());
			buffer.put(data[i].getTy());
		}
		buffer.flip();
		return buffer;
	}

	public static IntBuffer toIntBuffer(int[] indices) {

		IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer toFloatBuffer(Matrix4f mat) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				buffer.put(mat.get(i, j));

		buffer.flip();

		return buffer;
	}

	public static String loadShader(String file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"./res/shaders/" + file));
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer).append('\n');
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static Texture loadTexture(String file, String format) {
		
		try {
			int id = TextureLoader.getTexture(format, new FileInputStream(new File("./res/textures/" + file))).getTextureID();
			return new Texture(id);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to load texture: " + file);
		} 
		return null;
	}
}
