package Entities;

import java.awt.Rectangle;

public class Hitbox {

	private int rayon;
	private int x, y;

	private Rectangle rect ;

	public Hitbox(int rayon, int m_x, int m_y) {
		this.rayon = rayon;
		this.x = m_x;
		this.y = m_y;
	}

	public Hitbox(int x, int y, int width, int height) {
		rect= new Rectangle(x-width/2,y-height/2,width,height);
	}

	public void relocate(int m_x, int m_y) {
		rect.setRect(m_x-rect.width/2,m_y-rect.height/2,rect.width,rect.height);
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



}
