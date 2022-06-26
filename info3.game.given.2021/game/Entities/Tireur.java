package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Game;

public class Tireur extends Entity{
	
	public Tireur(int m_x, int m_y, String name, int r, Game game) throws IOException {
		super(game,name);
		m_images = loadSprite("resources/Tireur.png", 4, 6);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		type = 0;
		speed = 6;
		vie = 10;	
		moveCDR = 25;
		damageCDR = 2000;
	}

	public void Teleporte_joueur(int m_x, int m_y) {
		x = m_x;
		y = m_y;
	}

	public void tick(EntityManager EM, long elapsed) throws IOException {
		super.tick(EM, elapsed);
	}
	
	public void move(Direction dir) {
		super.move(dir);
		set_orientation();
	}
	
	public void set_orientation() {
		switch (this.direction) {
		case NW:
			m_imageIndex = 10;
			break;
		case NE:
			m_imageIndex = 16;
			break;
		case SW:
			m_imageIndex = 4;
			break;
		case SE:
			m_imageIndex = 23;
			break;
		case W:
			m_imageIndex = 7;
			break;
		case E:
			m_imageIndex = 20;
			break;
		case N:
			m_imageIndex = 13;
			break;
		case S:
			m_imageIndex = 1;
			break;
		default:
			break;
		}
	}
	

	public void hit(Direction dir) {
		if(hitCD > 0)
			return;
		hitCD = 750;
		Projectile balle;
		try {
			balle = new Projectile(x, y, 3, this.direction, 3,2,4000, "Balle", 10, game);
			this.EM.EM_add(balle);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g, int originex, int originey) {

		BufferedImage img = m_images[m_imageIndex];
		if (vie <= 0) {
	//		img = doublure.m_images[m_imageIndex];
		}
		int scale = 2;
		g.drawImage(img, x - originex - getWidth(), y - originey - getHeight(), scale * img.getWidth(),
				scale * img.getHeight(), null);
		g.drawOval(x - originex - hitbox.getRayon(), y - originey - hitbox.getRayon(), hitbox.getRayon() * 2,
				hitbox.getRayon() * 2);
	}

	public int getType() {
		if (vie <= 0) {
	//		return doublure.type;
		}
		return type;
	}

}
