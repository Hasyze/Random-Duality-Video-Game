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
	protected Hitbox hitbox;
	protected int type;
	/** Type
	 * 0: Joueur
	 * 1: Ennemi
	 * 2: Missile Ennemi
	 * 3: Missile Joeur
	 * 4: Fnatome
	 * 5: Rocher
	 * 6: Mur
	 * 7: Porte
	 **/
	
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
		speed = 0;
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
	
	public void degatVie(int degat) {
		vie-=degat;
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
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	public int getType() {
		return type;
	}
	
	public int getx_speed() {
		return x_speed;
	}
	
	public int gety_speed() {
		return y_speed;
	}
	
	public int getx_nspeed() {
		return x_nspeed;
	}
	
	public int gety_nspeed() {
		return y_nspeed;
	}
	
	
}
