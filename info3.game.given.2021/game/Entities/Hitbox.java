package Entities;

import java.awt.Rectangle;
import java.awt.Point;

public class Hitbox {

	private int rayon;
	private int x, y;


	private Rectangle rect = new Rectangle();

	public Hitbox(int rayon, int m_x, int m_y) {
		this.rayon = rayon;
		this.x = m_x;
		this.y = m_y;

	}	
	
	private int xr, yr, width, height;
	
	
	
	public Hitbox (int x, int y, int width, int height) {
		this.xr=x;
		this.yr=y;
		this.height=height;
		this.width=width;

	}

	public Hitbox(Point p, int width, int height) {
		rect= new Rectangle(p.x-width/2,p.y-height/2,width,height);
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
	public int getAbscisseRect() {
		return xr;
	}
	
	public int getOrdonneeRect() {
		return yr;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

}
