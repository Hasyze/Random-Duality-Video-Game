package Entities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Map.Salle;
import info3.game.*;

public class Porte extends Entity {
	
	public boolean etat; // =false si porte fermé, =true si porte ouverte
	
	String Name = "Porte";
	
	public Salle salle_origine;
	public Salle salle_destination;
	
	public int orientation_salle_origine; //Indique l'orientation de la porte dans la salle 1
	public int orientation_salle_destination; //Indique l'orientation de la porte dans la salle 2
	/* Valeurs : 
	 * 0 = Nord
	 * 1 = Est
	 * 2 = Sud
	 * 3 = Ouest
	 */
	

	public Porte(Salle salle, int orientation, Game game) throws IOException {	//Créer une porte dans une salle sans la lié à une autre salle
		super(game,"Porte");
		this.m_images = loadSprite("resources/images_test/jaune_20x20.jpg",1,1);
		BufferedImage img = this.m_images[0];
		salle_origine = salle;
		orientation_salle_origine = orientation;
		salle_destination = null;
		orientation_salle_destination = -1;
		
		this.Name = "Porte";
		this.etat = true;
		

		switch (orientation) {
		case 0 :
			x = 24*40;
			y = 0;
			break;
		case 1 :
			x = 48*40;
			y = 24*40;
			break;
		case 2 :
			x = 24*40;
			y = 48*40;
			break;
		default :
			x = 0;
			y = 24*40;
			break;
			
		}
		
		int heigt_hb = img.getHeight()/2;
		int width_hb = img.getWidth()/2;
		hitbox = new Hitbox(20,x+width_hb,y+heigt_hb,0);
		type = 7;
	}
	
	public static Image loadImage(String filename) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		}
		return null;
	}
	
	void Destination_porte(Salle s, int orientation) {
		salle_destination = s;
		orientation_salle_destination = orientation;	
	}

	
	

}
