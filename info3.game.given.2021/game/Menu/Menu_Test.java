package Menu;

import java.io.IOException;
import java.util.ArrayList;

import Entities.Cowboy;
import automaton.Automate;
import info3.game.Modele;

public class Menu_Test {
	
	
	public static void main(String args[]) throws IOException{

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
		
		Ressource Res = new Ressource(ListAut);
		Res.afficher_res();
		Res.afficher_res_aut();
		
		Res.setup(0, 0, 2);
		Res.setup(0, 2, 1);
		//marche jusqu'ici
		
		Res.setup(1, 4, 2);
		Res.setup(2, 3, 2);
		Res.setup(3, 2, 2);
		Res.setup(4, 1, 2);
		Res.setup(5, 0, 2);
		Res.afficher_res();
		
		Modele m_mod = new Modele();
		Cowboy m_cowboy1 = new Cowboy(m_mod, "Player1");
		Cowboy m_cowboy2 = new Cowboy(m_mod, "Player2");
		
		Res.set_couple(m_cowboy1);
		Res.set_couple(m_cowboy2);
		
		System.out.print("Test menu : cowboy 1 attribution automate : ");
		System.out.print(m_cowboy1.Name +" "+ m_cowboy1.Aut.name+"\n");
		
		System.out.print("Test menu : cowboy 2 attribution automate : ");
		System.out.print(m_cowboy2.Name + " "+m_cowboy2.Aut.name+"\n");
	}
}
