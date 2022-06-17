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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Cowboy extends Entity{
	
	private long m_imageElapsed;
	private long m_moveElapsed;
	

	public Cowboy() throws IOException {
		super();
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}
	public Cowboy(String name) throws IOException{
		super();
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
	}
		
	
	
	
	
	/*
	 * Simple animation here, the cowbow
	 */
	public void tick(long elapsed) {
		m_imageElapsed += elapsed;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
			// m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}
		m_moveElapsed += elapsed;
		if (m_moveElapsed > 24) {
			m_moveElapsed = 0;
			x = (x + x_speed - x_nspeed);
			y = (y + y_speed - y_nspeed);
			set_orientation();
		}
	}

	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[m_imageIndex];
		int scale = 2;
		g.drawImage(img, x, y, scale * img.getWidth(), scale * img.getHeight(), null);
	}

	public void set_orientation() {
		//Version un peu moche, verifier le format des sprites, cherche une nouvelle solution
		//Ajouter un chant orientation pour les projectiles ?
		if (x_speed > 0) {
			if (y_speed > 0) {
				m_imageIndex = 23;//GOOD
			} else if (y_speed < 0) {
				m_imageIndex = 16;//GOOD
			}else {
				m_imageIndex = 20; //GOOD
				}
		} else if (x_speed < 0) {
			if (y_speed > 0) {
				m_imageIndex = 4;//GOOD
			} else if (y_speed < 0) {
				m_imageIndex = 10;//GOOD
			}else {
				m_imageIndex = 7;//GOOD
			}
		}else if (y_speed > 0) {
			m_imageIndex = 1;//GOOD
			
		}else if (y_speed < 0) {
			m_imageIndex = 13;//GOOD
			
		}
	}

	public void move(int code) {
		switch (code) {
		case 37:
		case 81:
			x_nspeed = speed;
			break;
		case 39:
		case 68:
			x_speed = speed;
			break;
		case 38:
		case 90:
			y_nspeed = speed;
			break;
		case 40:
		case 83:
			y_speed = speed;
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
	
	public void move() {}
	public void stop() {}
	public void pop() {}
	public void wizz() {}
	public void paint() {}
	public Entity egg() {
		return null;
	}

}
