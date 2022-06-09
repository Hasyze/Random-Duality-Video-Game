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
	
	
}
