package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Entities.Entity;

public class View extends Game {
	
	// get the size of the canvas
	int width;
	int height;

	// Définit les coordonnées dans le monde du coin supérieur droit de la caméra
	int coinscamX;
	int coinscamY;

	ArrayList<Entity> Liste_affichage;
	
	public View() throws Exception {
		super();
		Liste_affichage = null;
	}
	
	
	public void coordonnee_cam() {
		
		// get the size of the canvas
		width = m_canvas.getWidth();
		height = m_canvas.getHeight();

		// Définit les coordonnées dans le monde du coin supérieur droit de la caméra
		coinscamX = (m_cowboy2.getx() + m_cowboy.getx()) / 2 - width / 2;
		coinscamY = (m_cowboy2.gety() + m_cowboy.gety()) / 2 - height / 2;

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
		/*
		game.paint()
		//Afficher background
		game.drawImage(salle.background, -coinscamX, -coinscamY, salle.background.getWidth(null), salle.background.getHeight(null), null);
		
		//Afficher entité		
		Liste_affichage = EM.sort_affichage();
		for (int i = 0; i< Liste_affichage.size(); i++) {
			
		}
		*/
	}
	
	
	
	
	
}
