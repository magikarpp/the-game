package com.dude.util;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = sprite.getSubimage((row * 96) - 96, (col * 96) - 96, width, height);
		return img;
	}

}
