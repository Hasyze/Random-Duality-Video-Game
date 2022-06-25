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
	 **/
	
	public static void main(String[] args) {
		BotBuilder b = new BotBuilder();
		List<Automate> ListAut = b.loadAutomata("gal/exemple/test.gal");
	   Ressource Res = AutomatonAssociator(ListAut);
	   GameLauncher(Res);	   
	   }
	public static Ressource AutomatonAssociator(List<Automate> listAut) {
		Ressource Res = new Ressource(listAut);
		int ChoosenAutInt;
		int len = listAut.size();
		String[] optionsToChoose = new String[len];
	    for(int k=0; k<len;k++) {
	    	optionsToChoose[k] = listAut.get(k).name +k;
	    }
		//cas spécial, 2 automates dans un type : les joueurs
		    for(int j=1; j<=2;j++) {
		    	String playerAut = (String) JOptionPane.showInputDialog(
		        		null,
			            "Choose an automaton for player"+j+" :",
			            "Choose Automaton",
			            JOptionPane.QUESTION_MESSAGE,
			            null,
			            optionsToChoose,
			            optionsToChoose[0]);
		        System.out.println("Your chosen automaton for the entity type 0, p"+j+" : " + playerAut);
		        if(playerAut == null || playerAut == "None") {
		        	ChoosenAutInt = -1;// automate -1 est celui qui est défini comme vide ( aut == null), correspond au "none";
		        }else {
		        char nb = playerAut.charAt(playerAut.length()-1);
		        ChoosenAutInt = Character.getNumericValue(nb);
		        }
		        Res.setup(0,ChoosenAutInt, j);
		        Res.afficher_res();
		        // entity type x load dans txt (ressources)
		       
		    	

		        
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
	        if(ChoosenAut == null || ChoosenAut =="None") {
	        	ChoosenAutInt = -1;	        	
	        }else {
	        char nb = ChoosenAut.charAt(ChoosenAut.length()-1);
	        ChoosenAutInt = Character.getNumericValue(nb);
	        }
	        Res.setup(i,ChoosenAutInt, 0);
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
	
}