package Entities;

import java.awt.Graphics;
import java.awt.Image;
import info3.game.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rocher extends Entity {
	int taille;
	
	public static Image loadImage(String filename) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		}
		return null;
	}
	//Image texture = loadImage("resources/image_test/noir.png");
	
	
	public Rocher(EntityManager EM, Modele modele, int pos_x, int pos_y) throws IOException {
		super(EM, modele);
		this.m_images = loadSprite("resources/image_test/marron.jpg",1,1);
		this.taille = 20;
		this.x = pos_x;
		this.y = pos_y;
	}
	

	
	public Rocher (EntityManager EM, Modele modele, int m_x, int m_y, String name, int r) throws IOException{
		super(EM, modele);
		m_images = loadSprite("resources/rocher.png", 2,5);
		this.Name = name;
		x = m_x;
		y = m_y;
		
		BufferedImage img = m_images[0];
		/*int heigt_hb = img.getHeight()/2;
		int width_hb = img.getWidth()/2;
		hitbox = new Hitbox(r,x+width_hb,y+heigt_hb,0);*/
		int heigt_hb = img.getHeight();
		int width_hb = img.getWidth();
		hitbox = new Hitbox(x,y,width_hb,heigt_hb);
		type = 5;
	}
	
	
	
	
}
