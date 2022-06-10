package info3.game;

public class Porte {
	
	
	Salle salle_origine;
	Salle salle_destination;
	
	Porte(Salle origine) {
		salle_origine = origine;
		salle_destination = null;
	}
	Porte(Salle origine, Salle destination) {
		salle_origine = origine;
		salle_destination = destination;
	}
	
	void destination(Salle s){
		salle_destination = s;
	}
	
	/**
	Salle salle1;
	String orientation_salle1;
	Salle salle2;
	String orientation_salle2;
	
	
	
	Porte(Salle s1, String orien_s1, Salle s2, String orien_s2) {
		salle1 = s1;
		orientation_salle1 = orien_s1;
		salle2 = s2;
		orientation_salle2 = orien_s2;
	}
	**/
	
	
}
