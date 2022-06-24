package Entities;

import java.awt.Rectangle;

public class Hitbox {

	private int rayon;
	private int x, y;

	private Rectangle rect = new Rectangle();

	public Hitbox(int rayon, int m_x, int m_y) {
		this.rayon = rayon;
		this.x = m_x;
		this.y = m_y;
	}

	public Hitbox(int x, int y, int width, int height) {
		rect = new Rectangle(x,y,width,height);
	}

	public void relocate(int m_x, int m_y) {
		rect.x = m_x;
		rect.y = m_y;
	}

	public int getRayon() {
		return rayon;
	}

	public int getAbscisse() {
		return x;
	}

	public int getOrdonnee() {
		return y;
	}
	
	public Rectangle getRect() {
		return rect;
	}

	/*
	 * double distance (int a_x,int a_y, int b_x, int b_y) { return
	 * Math.sqrt((a_x-b_x)*(a_x-b_x)+(a_y-b_y)*(a_y-b_y)); }
	 * 
	 * 
	 * boolean verif_collision(Entity a) { return distance
	 * (this.x,this.y,a.x+a.x_speed-a.x_nspeed,a.y-a.y_nspeed+a.y_speed)<this.rayon+
	 * a.hitbox.rayon; } boolean collision(DynamicEntity a) { return distance
	 * (this.x,this.y,a.x,a.y)<this.rayon+a.hitbox.rayon; } boolean
	 * collision(StaticEntity a) { return
	 * distance(x,y,a.x,a.y)<rayon+a.hitbox.rayon; }
	 */

}
