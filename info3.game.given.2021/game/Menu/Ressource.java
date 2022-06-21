package Menu;
import java.util.ArrayList;
import Entities.Entity;




public class Ressource {
	int[] Aut_List; // temporaire, ici on mettra la liste d'automates
	int nb_entity;//0 a 7 entitées.
	
	Ressource(//int Automates
			){
		int[] IntList = {1,2,3,4};
		Aut_List = IntList;
		nb_entity = 7;
	}
	
	void set_couple(Entity e, int i) {
		int[] Automaton = {i};
		e.Aut = Automaton;
		//ici rajouter l'écriture en dur dans le txt
	}
	void setAut(Entity e) {
		int type = e.getType();
		//ici lire l'aut ds le txt en fct du type.
	}
	
	
}
