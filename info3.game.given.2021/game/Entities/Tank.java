package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Game;


public class Tank extends Entity{
	
	Fantome doublure;

	public Tank(int m_x, int m_y, String name, int r, Game game) throws IOException {
		super(game,name);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		type = 0;
		speed = 4;
		vie = 20;		
	}

	public void Teleporte_joueur(int m_x, int m_y) {
		x = m_x;
		y = m_y;
	}
	
	public void degatVie(int degat) {
		super.degatVie(degat);
		if (damageCD <= 0)
			damageCD = 2000;
	}

	long test = 0;
	public void tick(EntityManager EM, long elapsed) throws IOException {
		super.tick(EM, elapsed);
		
	}

	public void move(Direction dir) {
		super.move(dir);
		set_orientation();
		if (moveCD <= 0)
			moveCD = 10;
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
		hitCD = 500;
		int X = 0;
		int Y = 0;		
		switch(dir) {
		case F:
			dir = this.direction;
			break;
		default:
			break;
		}
		switch (dir) {
		case NW:
			X -= 20;
			Y -= 20;
			break;
		case NE:
			X += 20;
			Y -= 20;
			break;
		case SW:
			X -= 20;
			Y += 20;
			break;
		case SE:
			X += 20;
			Y += 20;
			break;
		case W:
			X -= 20;
			break;
		case E:
			X += 20;
			break;
		case N:
			Y -= 20;
			break;
		case S:
			Y += 20;
			break;
		default:
			break;
		}
		
		try {
			Projectile balle1 = new Projectile(x+X, y+Y, 3, boussole(Direction.F),2,5,200, "Coup", 35, game);
			Projectile balle2 = new Projectile(x+X, y+Y, 3, boussole(Direction.L),2,5,200, "Coup", 35, game);
			Projectile balle3 = new Projectile(x+X, y+Y, 3, boussole(Direction.R),2,5,200, "Coup", 35, game);
			this.EM.EM_add(balle1);
			this.EM.EM_add(balle2);
			this.EM.EM_add(balle3);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g, int originex, int originey) {

		BufferedImage img = m_images[m_imageIndex];
		if (vie <= 0) {
			img = doublure.m_images[m_imageIndex];
		}
		int scale = 2;
		g.drawImage(img, x - originex - getWidth(), y - originey - getHeight(), scale * img.getWidth(),
				scale * img.getHeight(), null);
		g.drawOval(x - originex - hitbox.getRayon(), y - originey - hitbox.getRayon(), hitbox.getRayon() * 2,
				hitbox.getRayon() * 2);
	}

	public int getType() {
		if (vie <= 0) {
			return doublure.type;
		}
		return type;
	}
}
