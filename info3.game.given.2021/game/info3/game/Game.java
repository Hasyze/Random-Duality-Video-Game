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
package info3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.RandomAccessFile;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.game.graphics.GameCanvas;
import info3.game.sound.RandomFileInputStream;

public class Game {

	static Game game;
	Image bg = info3.game.graphics.GameCanvas.loadImage("resources/images_test/among-us.png");

	public static void main(String args[]) throws Exception {
		
		//Etage Map = new Etage(15);
		
		//Map.test_etage();
		
		try {
			System.out.println("Game starting...");
			game = new Game();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	JFrame m_frame;
	JLabel m_text;
	GameCanvas m_canvas;
	CanvasListener m_listener;
	Cowboy m_cowboy, m_cowboy2;
	Rocher m_rocher;
	Sound m_music;
	Entity_Manager EM; //Aziz

	Game() throws Exception {
		// creating a cowboy, that would be a model
		// in an Model-View-Controller pattern (MVC)
		m_cowboy = new Cowboy();
		m_cowboy2 = new Cowboy();
		m_cowboy2.x-=200;
		EM= new Entity_Manager(); //Aziz
		EM.EM_add(m_cowboy); //Aziz
		EM.EM_add(m_cowboy2); //Aziz
		
		m_rocher= new Rocher();
		// creating a listener for all the events
		// from the game canvas, that would be
		// the controller in the MVC pattern
		m_listener = new CanvasListener(this);
		// creating the game canvas to render the game,
		// that would be a part of the view in the MVC pattern
		m_canvas = new GameCanvas(m_listener);

		System.out.println("  - creating frame...");
		Dimension d = new Dimension(1024, 768);
		m_frame = m_canvas.createFrame(d);
		System.out.println("  - setting up the frame...");
		setupFrame();
	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {

		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		// center the window on the screen
		m_frame.setLocationRelativeTo(null);

		// make the vindow visible
		m_frame.setVisible(true);
	}

	/*
	 * ================================================================ All the
	 * methods below are invoked from the GameCanvas listener, once the window is
	 * visible on the screen.
	 * ==============================================================
	 */

	/*
	 * Called from the GameCanvas listener when the frame
	 */
	String m_musicName;

	void loadMusic() {
		m_musicName = m_musicNames[m_musicIndex];
		String filename = "resources/" + m_musicName + ".ogg";
		m_musicIndex = (m_musicIndex + 1) % m_musicNames.length;
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			m_canvas.playMusic(fis, 0, 1.0F);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	private int m_musicIndex = 0;
	private String[] m_musicNames = new String[] { "Marble-Machine" };

	private long m_textElapsed;

	/*
	 * This method is invoked almost periodically, given the number of milli-seconds
	 * that elapsed since the last time this method was invoked.
	 */
	void tick(long elapsed) {

		EM.tick(m_cowboy,elapsed);
		EM.tick(m_cowboy2,elapsed);

		// Update every second
		// the text on top of the frame: tick and fps
		m_textElapsed += elapsed;
		if (m_textElapsed > 1000) {
			m_textElapsed = 0;
			float period = m_canvas.getTickPeriod();
			int fps = m_canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + fps + " fps   ";
			m_text.setText(txt);
		}		
	}

	/*
	 * This request is to paint the Game Canvas, using the given graphics. This is
	 * called from the GameCanvasListener, called from the GameCanvas.
	 */
	
	void paint(Graphics g) {

		// get the size of the canvas
		//En gros ici on différencie la position du joueur de la position de la carte :
		/*
		 * 
		 *  _________________________________________________
		 * |                                                 |  <-- MAP
		 * |                                                 |
		 * |       _________                                 |
		 * |      |         | <-CAMERA                       |
		 * |      |    x    |                                |
		 * |      |_________|                                |
		 * |                                                 |
		 * |                                                 |
		 * |                                                 |
		 * |                                                 |
		 * |                                                 |
		 * |                                                 |
		 * |                                                 |
		 * |_________________________________________________|
		 * 
		 * 
		 * rappel : m_cowboy.screenX et m_cowboy.screenY sont les coordonnées du joueur à l'ecran (immobile)
		 * 			m_cowboy.worldX et m_cowboy.worldY sont les coordonnées du joueur dans la map
		 * 
		 * la partie compliquée est d'afficher la map au bon endroit par rapport au joueur (si le joueur est au coordonnées (30,50) alors
		 * il faudra afficher le bon endroit de la map sous ses pieds.
		 * 
		 */
		
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();
		int screenX = m_canvas.getX() - m_cowboy.x + m_cowboy.x; // on récupère la position de base du canvas (map) puis on la décale selon
																			// la position du joueur à l'écran
		int screenY = m_canvas.getY() - m_cowboy.y + m_cowboy.y;	//Idem

		int screenX_rocher=m_canvas.getX()-m_rocher.x;
		int screenY_rocher=m_canvas.getX()-m_rocher.y;
		//AFFICHAGE IMAGE BACKGROUND
		
		try {
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			g.drawImage(bg, screenX, screenY, bg.getWidth(null), bg.getHeight(null), null);	// on affiche le background aux coordonnées scrennX, screenY, 
																							// en lui donnant la largeur, hauteure l'image)
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
		// erase background
		
		
		// paint
		m_cowboy.paint(g, width, height);
		m_cowboy2.paint(g, width, height);
		//m_rocher.paint(g, width, height);
		//m_cowboy2.paint(g, width, height);
	}

}
