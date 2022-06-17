package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fantome extends Entity{
	
	
	public Fantome (int m_x, int m_y, String name, int r) throws IOException{
		super();
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
		x= m_x;
		y=m_y;
		BufferedImage img = m_images[0];
		int heigt_hb = (img.getHeight())/2;
		int width_hb = (img.getWidth())/2;
		hitbox = new Hitbox(r,x+width_hb,y+heigt_hb,0);
	}
}
