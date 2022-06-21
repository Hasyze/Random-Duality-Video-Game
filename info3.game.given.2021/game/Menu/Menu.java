package Menu;
import java.util.ArrayList;

import javax.swing.*;

import automaton.Automate;
import info3.game.Game;
public class Menu {
	static Game game;
	
	
	/**
	 * Type 0: Joueur 1: Ennemi 2: Missile Ennemi 3: Missile Joeur 4: Fnatome 5:
	 * Rocher 6: Mur 7: Porte
	 **/
	
	public static void main(String[] args) {
		ArrayList<Automate> ListAut = new ArrayList<Automate>();
		Automate m_automate_test0 = new Automate("Automate_test0");
		Automate m_automate_test1 = new Automate("Automate_test1");
		Automate m_automate_test2 = new Automate("Automate_test2");
		Automate m_automate_test3 = new Automate("Automate_test3");
		Automate m_automate_test4 = new Automate("Automate_test4");

		ListAut.add(m_automate_test0);
		ListAut.add(m_automate_test1);
		ListAut.add(m_automate_test2);
		ListAut.add(m_automate_test3);
		ListAut.add(m_automate_test4);
		
	   AutomatonAssociator(ListAut);
	   GameLauncher();
	   }
	public static void AutomatonAssociator(ArrayList<Automate> ListAut) {
		Ressource Res = new Ressource();
		int ChoosenAutInt;
		String[] optionsToChoose = {"Automaton0","Automaton1", "Automaton2", "Automaton3", "Automaton4", "None of the listed"}; // a remplacer si + d'auts
	     
		    for(int j=1; j<=2;j++) {
		    	String playerAut = (String) JOptionPane.showInputDialog(
		        		null,
			            "Choose an automaton for player"+j+" :",
			            "Choose Automaton",
			            JOptionPane.QUESTION_MESSAGE,
			            null,
			            optionsToChoose,
			            optionsToChoose[3]);
		        System.out.println("Your chosen automaton for the entity type 0, p"+j+" : " + playerAut);
		        if(playerAut == null) {
		        	ChoosenAutInt = 0;
		        }
		        char nb = playerAut.charAt(playerAut.length()-1);
		        ChoosenAutInt = Character.getNumericValue(nb);
		        Res.setup(0,ChoosenAutInt, j);
		        // entity type x load dans txt (ressources)
		        
		    	

		        //ressource.stocker.couple 2 joueurs
		        //entity.player.load.aut
		    }	    
		    for(int i =1; i<8;i++) {
		    
		    String ChoosenAut = (String) JOptionPane.showInputDialog(
	        		null,
		            "Choose an automaton for the entity type : "+ i,
		            "Choose Automaton",
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            optionsToChoose,
		            optionsToChoose[0]);
	        System.out.println("Your chosen automaton for the entity type "+i+" : " + ChoosenAut);
	        if(ChoosenAut == null) {
	        	ChoosenAutInt = i;
	        }
	        char nb = ChoosenAut.charAt(ChoosenAut.length()-1);
	        ChoosenAutInt = Character.getNumericValue(nb);
	        Res.setup(i,ChoosenAutInt, 0);
	        // entity type x load dans txt (ressources)
		    }    
	}
	public static void GameLauncher() {
		
		try {
			System.out.println("Game starting...");
			game = new Game();

			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}
	
}
