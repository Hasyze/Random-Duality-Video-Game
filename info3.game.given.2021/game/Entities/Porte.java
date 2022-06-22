package Entities;

import Map.Salle;
import info3.game.*;

public class Porte extends Entity {
	
	boolean etat; // =false si porte fermé, =true si porte ouverte
	
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
	
	public Porte(Modele modele, Salle salle, int orientation) {	//Créer une porte dans une salle sans la lié à une autre salle
		super(modele);
		salle_origine = salle;
		orientation_salle_origine = orientation;
		salle_destination = null;
		orientation_salle_destination = -1;		
	}
	
	void Destination_porte(Salle s, int orientation) {
		salle_destination = s;
		orientation_salle_destination = orientation;	
	}

	
	

}
