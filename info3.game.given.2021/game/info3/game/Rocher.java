package info3.game;

import java.io.IOException;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;

public class Rocher {
	
	Rocher() throws IOException {
		super();
		
		
		
		this.m= loadSprite("resources/winchester-4x6.png", 4, 6);
		this.x = 500;
		this.y = 350;
		
	}
}
