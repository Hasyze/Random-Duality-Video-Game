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
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Entities.Cowboy;
import Entities.Ennemis;
import Entities.Entity;
import Entities.Porte;
import Entities.Tank;
import Entities.Tireur;
import Map.Etage;
import Map.Salle;
import Menu.AutomateMap;
import Menu.Ressource;
import automaton.Automate;
import info3.game.graphics.GameCanvas;
import info3.game.sound.RandomFileInputStream;

public class Game {

	static Game game;

	Image bg;

	JFrame m_frame;
	JLabel m_text;
	GameCanvas m_canvas;
	CanvasListener m_listener;
	Sound m_music;
	
	public EntityManager EM;
	public Modele modele;

	Etage etage;
	Salle salle_courante;
	Porte changement_de_salle;	//Prend la valeur d'une porte avec laquelle le joueur rentre en contact pour faire le changement de salle
	int niveau;

	public AutomateMap automatemap;
	
	Cowboy m_cowboy, m_cowboy2;
	Entity Player1, Player2;
	Tank tank;
	Tireur tireur;
	Ennemis mechant;
	
	public Cowboy getPlayer() {
		return m_cowboy2;
	}

	
	public Game(Ressource Res2) throws Exception {
		niveau = 1;
		changement_de_salle = null;
		EM = new EntityManager();
		modele = new Modele(this);
		
		Init_niveau(niveau, Res2);
		
		
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
	
	public Game(AutomateMap map) throws Exception {
		this.automatemap = map;
		niveau = 1;
		changement_de_salle = null;
		EM = new EntityManager();
		modele = new Modele(this);
		//m_cowboy = new Cowboy(800, 1000, "Mur", 25, this);
		//m_cowboy2 = new Cowboy(800, 1100, "Mur", 25, this);
		Player1 = new Tireur(960, 1000, "Joueur1", 25, this);
		Player2 = new Tank(980, 1100, "Joueur2", 25, this);
		mechant = new Ennemis(900, 900, "Ennemie1", 25, this);
		//EM.EM_add(m_cowboy);
		//EM.EM_add(m_cowboy2);
		EM.EM_add(Player1);
		EM.EM_add(Player2);
		EM.EM_add(mechant);
		
		etage = new Etage(niveau, this);

		
		salle_courante = etage.salles[0];
		bg = salle_courante.background;
		
		salle_courante.charger_salle(EM, modele);
		
		niveau += 1; //On prévoie le changement de niveau
		
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
	
	public CanvasListener getListener() {
		return m_listener;
	}
	
	private void Init_niveau(int niv, Ressource Res2) throws IOException {
		
		
		
		
	
		
		Res2.set_couple(m_cowboy);
		Res2.set_couple(m_cowboy2);
		System.out.print("Test menu : cowboy 1 attribution automate : ");
		System.out.print(m_cowboy.Name +" "+ m_cowboy.Aut.name+"\n");
		
		System.out.print("Test menu : cowboy 2 attribution automate : ");
		System.out.print(m_cowboy2.Name + " "+m_cowboy2.Aut.name+"\n");
		
		

		
		
		EM.EM_add(m_cowboy);
		EM.EM_add(m_cowboy2);
		EM.EM_add(mechant);
		
		etage = new Etage(niv, this);

		
		salle_courante = etage.salles[0];
		bg = salle_courante.background;
		
		salle_courante.charger_salle(EM, modele);
		
		niveau += 1; //On prévoie le changement de niveau
	}
	
	//On affiche tous les élements statiques, on affiche ensuite les dynamiques a chaque ticks
	
	private void dessine_salle (Graphics g, int coinscamX, int coinscamY) {
		ArrayList<Entity> Static = EM.sort_affichage();
		Entity e;
		for (int i=0; i<Static.size(); i++) {
			//System.out.print("Nom : " + Static.get(i).Name + " x : " + Static.get(i).getx() + " y :" + Static.get(i).gety() + "\n");
			e = Static.get(i);
			e.paint(g, coinscamX, coinscamY);
		}
		//System.out.print("Affichage DONE");
	}
	
	
	
	
	private void Chgmt_salle(Porte porte) throws IOException {
		EM.vider_entity_manager(); // --> vide l'entity manager sauf les deux cowboy
		salle_courante = porte.salle_destination;
		salle_courante.charger_salle(EM, modele);
		bg = salle_courante.background;
		
		switch (porte.orientation_salle_destination) {
		case 0 :
			Player1.Teleporte_joueur(23*40, 2*40);
			Player2.Teleporte_joueur(25*40,  2*40);
			break;
		case 1 :
			Player1.Teleporte_joueur(46*40, 23*40);
			Player2.Teleporte_joueur(46*40, 25*40);
			break;
		case 2 :
			Player1.Teleporte_joueur(23*40, 46*40);
			Player2.Teleporte_joueur(25*40, 46*40);
			break;
		default :
			Player1.Teleporte_joueur(2*40, 23*40);
			Player2.Teleporte_joueur(2*40, 25*40);
			break;
		}
		changement_de_salle = null;
		
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
	
	public static Image loadImage(String filename) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			Image image = ImageIO.read(imageFile);
			return image;
		}
		return null;
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
	private String[] m_musicNames = new String[] { "Runaway-Food-Truck" };

	private long m_textElapsed;

	/*
	 * This method is invoked almost periodically, given the number of milli-seconds
	 * that elapsed since the last time this method was invoked.
	 */

	long test = 0;	
	void tick(long elapsed) throws Exception {
		test += elapsed;
		if (test > 2500) {
			test = 0;
			//System.out.println("SWITCH");
			switchplayers();
		}

		// EM TICK STEPS
		EM.tick(elapsed);
		modele.collionsDynamic(EM.getDynamic());
		
		if (changement_de_salle != null) {	//à chaque tick on vérifie qu'il ne faut pas changer de salle
			System.out.print("On doit changer de salle");
			try {
				Chgmt_salle(changement_de_salle);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	

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

	
	private void switchplayers() {
		//Automate a = this.m_cowboy.Aut;
		//this.m_cowboy.Aut = this.m_cowboy2.Aut;
		//this.m_cowboy2.Aut = a;
	}
	
	/*
	 * This request is to paint the Game Canvas, using the given graphics. This is
	 * called from the GameCanvasListener, called from the GameCanvas.
	 */

	// A terme ça faut que ce soit les bordures de la map ou de la salle
	int xmin = 0;
	int ymin = 0;
	int xmax = 1920;
	int ymax = 1920;

	void paint(Graphics g) {

		// get the size of the canvas
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();

		// Définit les coordonnées dans le monde du coin supérieur droit de la caméra
		int coinscamX = (Player2.getx() + Player1.getx()) / 2 - width / 2;
		int coinscamY = (Player2.gety() + Player2.gety()) / 2 - height / 2;

		// erase background

		if (coinscamX < xmin) {
			coinscamX = xmin;
		}
		if (coinscamY < ymin) {
			coinscamY = ymin;
		}
		if (coinscamX + width > xmax) {
			coinscamX = xmax - width;
		}
		if (coinscamY + height > ymax) {
			coinscamY = ymax - height;
		}
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.drawImage(bg, -coinscamX, -coinscamY, bg.getWidth(null), bg.getHeight(null), null);
		g.drawOval(width / 2 - 5, height / 2 - 5, 10, 10);
		g.drawLine(Player1.getx() - coinscamX, Player1.gety() - coinscamY, Player2.getx() - coinscamX, Player2.gety() - coinscamY);

		
		dessine_salle(g, coinscamX, coinscamY);

	}

}
