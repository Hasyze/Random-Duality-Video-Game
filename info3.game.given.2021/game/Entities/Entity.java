package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity extends Object{
	
	protected BufferedImage[] m_images;
	protected int m_imageIndex;
	
	protected int x = 10,y = 10;
	protected int x_speed;
	protected int y_speed;
	protected int x_nspeed;
	protected int y_nspeed;
	
	//Stats
	protected int speed;
	protected int vie;
	
	public Entity() {
		m_images = null;
		m_imageIndex = 0;
		x_speed = 0;
		y_speed = 0;
		x_nspeed = 0;
		y_nspeed = 0;
		vie = 0;
		speed = 4;
	}
	
	public void move() {}
	public void stop() {}
	//public void pop() {}	Implémenter ces fonctions dans chacune des sous-classes
	//public void wizz() {}
	public void paint() {}
	public Entity egg() {
		return null;
	}
	public void transfert(Entity e) {}
	
	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException{
		return null;
	}
	
	
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public int getvie(){
		return vie;
	}
	
	
}
