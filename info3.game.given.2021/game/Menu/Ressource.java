package Menu;

import java.util.ArrayList;
import Entities.Entity;
import automaton.Automate;



public class Ressource {
	protected ArrayList<Automate> Aut_List = new ArrayList<Automate>(); // temporaire, ici on mettra la liste d'automates ArrayList<Automaton> List = ...
	private int nb_entity = 8;//0 a 7 entitées.
	int [][] Couples;
	

	public Ressource(ArrayList<Automate> AutL) {
		int[][] intList={{4,3},{1},{2},{3},{0},{4},{4},{4}}; // liste de couples
		Couples = intList; // liste par défault, a changer si on fait + d'automates : 5/entitées : 8;
		Aut_List = AutL;
	}
	
	//setup les couples selon l'ordre qu'on choisit (si null, ordre de base)
	protected void setup(int Type, int ChoosenAut, int player2) {
		//la casse du choix null est a gérer par menu pas ici
			if(player2==2 && Type == 0) {
				Couples[0][1] = ChoosenAut;
			}else {
				Couples[Type][0] = ChoosenAut;
			}
	}
	
	
	public void set_couple(Entity e) {
		int type = e.getType();
		if(Couples[type][0]==-1) {
			e.Aut = null; //si aut -1, c'est l'aut null
		}else {
			if(type !=0) { // cas basique, chaque type a un seul automate ;)
				e.Aut = Aut_List.get(Couples[type][0]);// Associe l'automate de Autlist donné dans couples		
			}else { // cas spécial player
				if(e.Name == "Player1") {			
					e.Aut = Aut_List.get(Couples[0][0]);
				}else {	
					e.Aut = Aut_List.get(Couples[0][1]);
				}
			}
		}
		
	}
	
	public void afficher_res() {
		System.out.print("AFFICHAGE RESSOURCES : \n");
		System.out.print("{ ");
		for(int i =0; i<nb_entity; i++){
			System.out.print("{ ");
			for(int j = 0; j<Couples[i].length;j++) {
				System.out.print(Couples[i][j]);
				System.out.print(" ");
			}
			System.out.print("}");
		}
		System.out.print(" }\n");
		System.out.print("\n");	
	}
	public void afficher_res_aut() {
		System.out.print("AFFICHAGE RESSOURCES AUTOMATES : \n");
		for(int i =0; i<Aut_List.size(); i++) {
			System.out.print(Aut_List.get(i).name+"\n");	
		}
		System.out.print("\n");	

	}
	
}
