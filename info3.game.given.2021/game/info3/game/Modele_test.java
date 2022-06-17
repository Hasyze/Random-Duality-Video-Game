package info3.game;

import java.util.ArrayList;

import Entities.Cowboy;
import Entities.Entity;
import Entities.Fantome;
import Entities.Rocher;

public class Modele_test {
	
	
	public static void main(String args[]) throws Exception {
		Modele md = new Modele();
		System.out.print("DEBUT DES TEST \n");
		EntityManager EM = new EntityManager();
		
		Cowboy m_cowboy = new Cowboy(0,0,"Cowboy1",25);
		Rocher m_rocher = new Rocher(400,400,"Rocher1", 50);
		Fantome m_fantome = new Fantome(250,250,"Fantome1",25);
		int[] Listint = {1,2,3}; // un faux automate, surtout non nul quoi;
		
		 m_cowboy.Aut = Listint;
		 m_fantome.Aut = Listint;
		
		 EM.EM_add(m_fantome);
		 EM.EM_add(m_cowboy);
		 EM.EM_add(m_rocher);
		 
		 EM.afficher_EM();
		 
		 /// début tests modèles
		 ArrayList<Entity> List = EM.sort_affichage();
		 ArrayList<Entity> List_res1 = md.collision(m_cowboy, List);
		 System.out.print("AFFICHAGE LIST1 : Pas de collision normalement\n");
		 for(int j=0; j<List_res1.size(); j++) {
			 System.out.print(List_res1.get(j));
		 }
		 System.out.print("Fin affichage\n");
		m_cowboy.setVie(-1); 
		m_rocher.setVie(-1);
		m_fantome.setVie(-1);
		

		
		EM.tick();
		EM.afficher_EM();
		
		
		
		
		Cowboy m_cowboy2 = new Cowboy(0,0,"Cowboy1",25);
		Rocher m_rocher2 = new Rocher(400,400,"Rocher1", 50);
		Fantome m_fantome2 = new Fantome(250,250,"Fantome1",25);
		int[] Listint2 = {1,2,3}; // un faux automate, surtout non nul quoi;
		
		 m_cowboy.Aut = Listint;
		 m_fantome.Aut = Listint;
		
		 EM.EM_add(m_fantome);
		 EM.EM_add(m_cowboy);
		 EM.EM_add(m_rocher);
		 
		 
		 
		 
		 
		 
	}
}
