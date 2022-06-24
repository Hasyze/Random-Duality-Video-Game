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

	public Cowboy(Modele modele, EntityManager EM) throws IOException {
		super(modele, EM);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}

	public Cowboy(Modele model, String name, EntityManager EM) throws IOException {
		super(model, EM);
		this.Name = name;
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}

	public Cowboy(int m_x, int m_y, String name, int r, Game game) throws IOException {
		super(game);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		//hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		int heigt_hb1 = (m_images[0].getHeight()) ;
		int width_hb1 = (m_images[0].getWidth()) ;
		hitbox = new Hitbox(x,y,width_hb1,heigt_hb1);

		//hitbox = new Hitbox(r, x + width_hb, y + heigt_hb);
	//	switch(direction) {
		//	case E : 
				hitboxvoisinE=new Hitbox(x,y- width_hb/2,1024 + x,hitbox.getRect().height);
		//	case S : 
				hitboxvoisinS=new Hitbox(x-width_hb/2,y+width_hb/2, hitbox.getRect().width, 1024 + y);
		//	case W : 
				hitboxvoisinW=new Hitbox(0-width_hb/2,y-width_hb/2, x,hitbox.getRect().height);
		//	case N : 
				hitboxvoisinN=new Hitbox(x-width_hb/2,0,hitbox.getRect().width,y-width_hb/2);
		//}
				hitboxvoisinNW = new Hitbox(0, 0, x-width_hb, y-width_hb);
		        // case NE : 
		                hitboxvoisinNE = new Hitbox(x+width_hb, 0, 1024, y-width_hb);
		                
		        // case SW : 
		                hitboxvoisinSW = new Hitbox(0, y+width_hb, x-width_hb, 758-y);
		        // case SE : 
		                hitboxvoisinSE = new Hitbox(x+width_hb, y+width_hb, 1024-x, 758-y);    
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

	public void Teleporte_joueur(int m_x, int m_y) {
		x = m_x;
		y = m_y;
	}
	
	public void tick(EntityManager EM , long elapsed) throws IOException {
		super.tick(EM, elapsed);
		moveCD += elapsed;
		if (moveCD > 24)
			moveCD = 0;
	}

	public void hit() throws IOException {
		System.out.print("" + "" + "\n\n Creation balle \n\n");
		ProjectileA balle = new ProjectileA(direction, modele, EM, x, y);
		this.EM.EM_add(balle);
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
