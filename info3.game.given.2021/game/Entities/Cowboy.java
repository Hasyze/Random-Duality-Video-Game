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

	private long m_imageElapsed;
	private long m_moveElapsed;

	public Cowboy(EntityManager EM, Modele modele) throws IOException {
		super(EM, modele);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}

	public Cowboy(EntityManager EM, Modele modele, String name) throws IOException {
		super(EM, modele);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
	}

	public Cowboy(EntityManager EM, Modele modele, int m_x, int m_y, String name, int r, Automate aut) throws IOException {
		super(EM, modele);
		this.Aut = aut;
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		//hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		int heigt_hb1 = (m_images[0].getHeight()) ;
		int width_hb1 = (m_images[0].getWidth()) ;
		hitbox = new Hitbox(x,y,width_hb1,heigt_hb1,0);

		//hitbox = new Hitbox(r, x + width_hb, y + heigt_hb);
	//	switch(direction) {
		//	case E : 
				hitboxvoisinE=new Hitbox(x,y- width_hb,1024 + x,hitbox.getRayon()*2);
		//	case S : 
				hitboxvoisinS=new Hitbox(x-width_hb,y+width_hb, hitbox.getRayon()*2, 1024 + y);
		//	case W : 
				hitboxvoisinW=new Hitbox(0-width_hb,y-width_hb, x,hitbox.getRayon()*2);
		//	case N : 
				hitboxvoisinN=new Hitbox(x-width_hb,0,hitbox.getRayon()*2,y-width_hb);
		//}
		type = 0;
	}

	/*
	 * Simple animation here, the cowbow
	 */
	public void tick(long elapsed) throws Exception {
		//super.tick(elapsed);
		hitbox.relocate(x, y);
		m_imageElapsed += elapsed;
		m_moveElapsed += elapsed;
		if (m_imageElapsed > 1500) {
			m_imageElapsed = 0;
		}
		if (m_moveElapsed > 2000) {
			m_moveElapsed = 0;

			Aut.step(this);
			if (x_speed > 0 || y_speed > 0 || x_nspeed > 0 || y_nspeed > 0) {
				ArrayList<Entity> Dynamic = EM.getDynamic();
				if (!(modele.collisions(this, Dynamic))) {
					x = (x + x_speed - x_nspeed);
					y = (y + y_speed - y_nspeed);
				}
			}
		}
		set_orientation();
	}

	

	public void set_orientation() {
		// Version un peu moche, verifier le format des sprites, cherche une nouvelle
		// solution
		// Ajouter un chant orientation pour les projectiles ?img.getWidth()
		if (x_speed > 0) {
			if (y_speed > 0) {
				m_imageIndex = 23;// GOOD
			} else if (y_nspeed > 0) {
				m_imageIndex = 16;// GOOD
			} else {
				m_imageIndex = 20; // GOOD
			}
		} else if (x_nspeed > 0) {
			if (y_speed > 0) {
				m_imageIndex = 4;// GOOD
			} else if (y_nspeed > 0) {
				m_imageIndex = 10;// GOOD
			} else {
				m_imageIndex = 7;// GOOD
			}
		} else if (y_speed > 0) {
			m_imageIndex = 1;// GOOD

		} else if (y_nspeed > 0) {
			m_imageIndex = 13;// GOOD

		}
	}

	public void movet(int code) {
		switch (code) {
		case 37:
		case 81:
			this.direction = Direction.W;
			break;
		case 39:
		case 68:
			this.direction = Direction.E;
			break;
		case 38:
		case 90:
			this.direction = Direction.N;
			break;
		case 40:
		case 83:
			this.direction = Direction.S;
			break;
		}
		// System.out.println("OUIIIIIIII");
		// .out.println(this.x + this.y);

	}
	
	public void move(Direction dir) {
		switch (dir) {
		
		case W:
			x_nspeed = speed;
			break;
		
		case E:
			x_speed = speed;
			break;
		
		case N:
			y_nspeed = speed;
			break;
		
		case S:
			y_speed = speed;
			break;
		default:
			break;
		}
		
	}
	
	

	public void stop(int code) {
		switch (code) {
		case 37:
		case 81:
			x_nspeed = 0;
			break;
		case 39:
		case 68:
			x_speed = 0;
			break;
		case 38:
		case 90:
			y_nspeed = 0;
			break;
		case 40:
		case 83:
			y_speed = 0;
			break;
		}
	}

	public void move() {
	}

	public void stop() {
	}

	public void pop() {
	}

	public void wizz() {
	}

	public void paint() {
	}

	public Entity egg() {
		return null;
	}

}
