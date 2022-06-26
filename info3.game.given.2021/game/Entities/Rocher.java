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
	
	public Rocher (int m_x, int m_y,Game game) throws IOException{
		super(game,"Rocher");
		m_images = loadSprite("resources/images_test/marron_20x20.jpg",1,1);
		//this.m_images = loadSprite("resources/images_test/Rocher2.png",1,1);
		this.Name = "Rocher";
		x = m_x;
		y = m_y;
		
		BufferedImage img = m_images[0];
		
		
		hitbox = new Hitbox(20,x,y,0);
		type = 5;
	}
	
	
	
	
}
