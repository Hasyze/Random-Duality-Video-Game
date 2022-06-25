package Entities;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import info3.game.*;

import javax.imageio.ImageIO;

public class Mur extends Entity {
	int taille;

	
	public Mur(int pos_x, int pos_y, Game game) throws IOException {
		super(game);
		this.m_images = loadSprite("resources/image_test/noir.png",1,1);
		this.taille = 20;
		this.x = pos_x;
		this.y = pos_y;
	}

	

	public Mur (int m_x, int m_y, String name, int r,Game game) throws IOException{
		super(game, "Mur");
		m_images = loadSprite("resources/images_test/noir_20x20.png", 1,1);
		x = m_x;
		y = m_y;
		
		BufferedImage img = m_images[0];
		
		int height_hb = 2*img.getHeight();///2;
		int width_hb = 2*img.getWidth();///2;
		//hitbox = new Hitbox(r,x+width_hb,y+heigt_hb,0);
		hitbox = new Hitbox (x,y,width_hb,height_hb);
		type = 6;
		speed = 10;
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