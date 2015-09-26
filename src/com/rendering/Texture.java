package com.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

	private int ID;

	public Texture(int ID) {
		this.ID = ID;
	}

	public void bind() {
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, ID);
		glDisable(GL_TEXTURE_2D);
	}

}
