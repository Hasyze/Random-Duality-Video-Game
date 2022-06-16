package info3.game;

import java.awt.image.BufferedImage;

public abstract class Entity extends Object{
	
	BufferedImage[] m_images;
	int m_imageIndex;
	
	int x = 10,y = 10;
	int x_speed;
	int y_speed;
	int x_nspeed;
	int y_nspeed;
	
	//Stats
	int speed;
	int vie;
	
	public Entity() {
		m_imageIndex = 0;
		x_speed = 0;
		y_speed = 0;
		x_nspeed = 0;
		y_nspeed = 0;
		vie = 0;
		speed = 0;
	}
	
	public void move() {}
	public void stop() {}
	public void pop() {}
	public void wizz() {}
	
	
}
