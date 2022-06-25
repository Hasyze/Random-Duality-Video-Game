/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package Entities;

import java.awt.Graphics;

import info3.game.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import automaton.Automate;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Cowboy extends Entity {

	Fantome doublure;

	/*public Cowboy(Modele modele, EntityManager EM) throws IOException {
		super(modele, EM);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}

	public Cowboy(Modele model, String name, EntityManager EM) throws IOException {
		super(model, EM);
		this.Name = name;
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}*/

	public Cowboy(int m_x, int m_y, String name, int r, Game game) throws IOException {
		super(game, name);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
		x = m_x;
		y = m_y;
		int height_hb = (m_images[0].getHeight()); // 2;
		int width_hb = (m_images[0].getWidth());//// / 2;
		//hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		hitbox = new Hitbox (x,y,width_hb,height_hb);
		type = 0;
		speed = 5;
	}

	public Cowboy(Game game, int m_x, int m_y, String name, int r, Automate aut) throws IOException {
		super(game);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
		this.Aut = aut;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		type = 0;
	}

	public void tick(EntityManager EM, long elapsed) throws IOException {
		super.tick(EM, elapsed);
	}

	public void move(Direction dir) {
		super.move(dir);
		set_orientation();
		if (moveCD <= 0)
			moveCD = 25;
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
		Projectile balle;
		try {
			balle = new Projectile(x, y, 3, this.direction, "Balle", 20, game);
			this.EM.EM_add(balle);

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
