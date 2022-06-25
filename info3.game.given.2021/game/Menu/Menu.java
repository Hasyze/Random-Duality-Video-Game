package Menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import automaton.*;
import info3.game.Game;

public class Menu {
	static Game game;

	/**
	 * Type 0: Joueur 1: Ennemi 2: Missile Ennemi 3: Missile Joeur 4: Fnatome 5:
	 * Rocher 6: Mur 7: Porte
	 * @throws Exception 
	 **/

	

	public static void main(String[] args) throws Exception {
		BotBuilder b = new BotBuilder();
		List<Automate> ListAut = b.loadAutomata("gal/exemple/test.gal");
		AutomateMap map = new AutomateMap();
		String[] Entities = { "Joueur1", "Joueur2", "Ennemie1", "Mur", "Balle", "Coup", "Porte", "Rocher"};
		//Ressource res = AutomatonAssociator(ListAut);
		AutomatonAssociator(map,ListAut, Entities);
		//GameLauncher(res);
		GameLauncher(map);
		
	}

	public static void AutomatonAssociator(AutomateMap map, List<Automate> ListAut, String[] Entities) throws Exception {
		int len = ListAut.size();
		int curseur = 0;
		String[] optionsToChoose = new String[len];
		for (int k = 0; k < len; k++) {
			optionsToChoose[k] = ListAut.get(k).name;
		}
		Automate chosen = null;
		for (int i = 0; i < Entities.length; i++) {
			if(i>ListAut.size()-1)
				curseur = 0;
			else
				curseur = i;
			String playerAut = (String) JOptionPane.showInputDialog(null,
					"Choisissez un automate pour " + Entities[i] + " :", "Choose Automaton",
					JOptionPane.QUESTION_MESSAGE, null, optionsToChoose, optionsToChoose[curseur]);
			for (Automate temp : ListAut) {
				if (temp.name == playerAut) {
					chosen = temp;
					break;
				}
			}
			if(chosen == null) {
				throw new Exception("AutomatonAssociator : Aucun automate trouvé");
			}
			map.addAutomate(Entities[i], chosen);
		}
	}

	public static Ressource AutomatonAssociator(List<Automate> listAut) {
		Ressource Res = new Ressource(listAut);
		int ChoosenAutInt;
		int len = listAut.size();
		String[] optionsToChoose = new String[len];
		for (int k = 0; k < len; k++) {
			optionsToChoose[k] = listAut.get(k).name + k;
		}
		/*
		 * Ressource Res = new Ressource(listAut); int ChoosenAutInt; String[]
		 * optionsToChoose = {"Automaton0","Automaton1", "Automaton2", "Automaton3",
		 * "Automaton4", "None"}; // a remplacer si + d'auts
		 */
		// cas spécial, 2 automates dans un type : les joueurs
		for (int j = 1; j <= 2; j++) {
			String playerAut = (String) JOptionPane.showInputDialog(null, "Choose an automaton for player" + j + " :",
					"Choose Automaton", JOptionPane.QUESTION_MESSAGE, null, optionsToChoose, optionsToChoose[3]);
			System.out.println("Your chosen automaton for the entity type 0, p" + j + " : " + playerAut);
			if (playerAut == null || playerAut == "None") {
				ChoosenAutInt = -1;// automate -1 est celui qui est défini comme vide ( aut == null), correspond au
									// "none";
			} else {
				char nb = playerAut.charAt(playerAut.length() - 1);
				ChoosenAutInt = Character.getNumericValue(nb);
			}
			Res.setup(0, ChoosenAutInt, j);
			Res.afficher_res();
			// entity type x load dans txt (ressources)

		}
		for (int i = 1; i < 8; i++) {

			String ChoosenAut = (String) JOptionPane.showInputDialog(null,
					"Choose an automaton for the entity type : " + i, "Choose Automaton", JOptionPane.QUESTION_MESSAGE,
					null, optionsToChoose, optionsToChoose[0]);
			System.out.println("Your chosen automaton for the entity type " + i + " : " + ChoosenAut);
			if (ChoosenAut == null || ChoosenAut == "None") {
				ChoosenAutInt = -1;
			} else {
				char nb = ChoosenAut.charAt(ChoosenAut.length() - 1);
				ChoosenAutInt = Character.getNumericValue(nb);
			}
			Res.setup(i, ChoosenAutInt, 0);
			Res.afficher_res();
			// entity type x load dans txt (ressources)
		}
		return Res;
	}

	public static void GameLauncher(Ressource Res) {

		try {
			System.out.println("Game starting...");
			game = new Game(Res);

			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}
	
	public static void GameLauncher(AutomateMap map) {

		try {
			System.out.println("Game starting...");
			game = new Game(map);

			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

}
