package Entities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rocher extends Entity {
	int taille;
	Image texture;
	Hitbox hitbox;
	int pos_x;
	int pos_y;
	
	public Rocher(int pos_x, int pos_y) throws IOException {
		this.texture = loadImage("resources/image_test/marron.jpg");
		this.taille = 20;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	public static Image loadImage(String filename) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		}
		return null;
	}
}
