package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import automaton.Automate;
import info3.game.*;

public abstract class Entity extends Object {

	public Automate Aut;

	
	public String Name;
	
	protected BufferedImage[] m_images;
	protected int m_imageIndex;

	protected int x = 10, y = 10;
	protected int x_speed;
	protected int y_speed;
	protected int x_nspeed;
	protected int y_nspeed;
	protected Hitbox hitbox;
	protected int type;
	protected Modele modele; // ??
	/**
	 * Type 
	 * 0: Joueur 
	 * 1: Ennemi 
	 * 2: Missile Ennemi 
	 * 3: Missile Joeur 
	 * 4: Fantome
	 *  5:Rocher 
	 * 6: Mur 
	 * 7: Porte
	 **/

	// Stats
	protected int speed;
	protected int vie;

	public Entity(Modele modele) {
		
		this.modele = modele;
		
		m_images = null;
		m_imageIndex = 0;
		x_speed = 0;
		y_speed = 0;
		x_nspeed = 0;
		y_nspeed = 0;
		vie = 1;
		speed = 10;
	}
	public Entity(Modele modele, String Name) {
		System.out.print(Name);
		//this.EM = em;
		this.Name = Name;
		
		this.modele = modele;
		
		m_images = null;
		m_imageIndex = 0;
		x_speed = 0;
		y_speed = 0;
		x_nspeed = 0;
		y_nspeed = 0;
		vie = 1;
		speed = 4;
	}

	public void move() {
	}

	public void stop() {
	}

	// public void pop() {} Implémenter ces fonctions dans chacune des sous-classes
	// public void wizz() {}
	public void paint(Graphics g, int originex, int originey) {
		BufferedImage img = m_images[m_imageIndex];
		int scale = 2;
		g.drawImage(img, x - originex - getWidth(), y - originey - getHeight(), scale * img.getWidth(),
				scale * img.getHeight(), null);
		g.drawOval(x-originex-hitbox.getRayon(), y-originey-hitbox.getRayon(), hitbox.getRayon()*2, hitbox.getRayon()*2);
	}

	public Entity egg() {
		return null;
	}

	public void transfert(Entity e) {
	}

	public void degatVie(int degat) {
		vie -= degat;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		
		return y;
	}

	public int getvie() {
		return vie;
	}
	

	public void setVie(int i) {
		vie += i;
	}

	public void step() {
		// TODO : step automates pour l'aut de chaque entity.
	}

	
	int m_moveElapsed = 0;
	public void tick(EntityManager em,long elapsed) {
		m_moveElapsed += elapsed;
		hitbox.relocate(x, y);
		if (m_moveElapsed > 24) {
			m_moveElapsed = 0;
			if(x_speed>0 || y_speed>0 || x_nspeed>0 || y_nspeed>0) {
				ArrayList<Entity> Dynamic = em.getDynamic();
				ArrayList<Entity> Static = em.getStatic();
				Dynamic.addAll(Static);
				ArrayList<Entity> col = modele.collision(this, Dynamic);
				if(col.isEmpty()) {
					
					x = (x + x_speed - x_nspeed);
					y = (y + y_speed - y_nspeed);
				}
			}
		}
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
	
	public int getWidth() {
		return m_images[m_imageIndex].getWidth();
	}
	
	public int getHeight() {
		return m_images[m_imageIndex].getHeight();
	}

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

}
