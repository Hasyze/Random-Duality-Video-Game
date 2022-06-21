package Menu;

import java.util.ArrayList;
import Entities.Entity;
import automaton.Automate;



public class Ressource {
	ArrayList<Automate> Aut_List = new ArrayList<Automate>(); // temporaire, ici on mettra la liste d'automates ArrayList<Automaton> List = ...
	int nb_entity = 7;//0 a 7 entitées.
	int [][] Couples;
	
	public Ressource(){
		// récup la liste depuis le pack automate + tard
		int[][] intList={{4,3},{1},{2},{3},{0},{4},{4},{4}}; // liste de couples
		Couples = intList; // liste par défault, a changer si on fait + d'automates : 5/entitées : 8;
	}
	
	//setup les couples selon l'ordre qu'on choisit (si null, ordre de base)
	protected void setup(int Type, int ChoosenAut, int player2) {
		//la casse du choix null est a gérer par menu pas ici
			if(player2==2) {
				Couples[0][1] = ChoosenAut;
			}else {
				Couples[Type][0] = ChoosenAut;
			}
	}
	
	
	void set_couple(Entity e) {
		int type = e.getType();
		if(type !=0) { // cas basique, chaque type a un seul automate ;)
			e.Aut = Aut_List.get(Couples[type][0]);// Associe l'automate de Autlist donné dans couples		
		}else { // cas spécial player
			if(e.Name == "Player1") {			
				e.Aut = Aut_List.get(Couples[0][1]);
			}else {	
				e.Aut = Aut_List.get(Couples[0][2]);
			}
		}
		
	}
	
}
