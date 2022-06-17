package info3.game;

import java.util.ArrayList;

import Entities.Cowboy;
import Entities.Entity;
import Entities.Fantome;
import Entities.Mur;
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
		m_rocher.setVie(-1); // est censé ne rien changé car la vie de rocher change rien. ( marche)
		m_fantome.setVie(-1);
		EM.tick();
		EM.afficher_EM();
		
		
		
		
		Cowboy m_cowboy2 = new Cowboy(400,400,"Cowboy2",25);	
		Fantome m_fantome2 = new Fantome(400,400,"Fantome2",25); // les deux entitées vont dans le rocher, c'est bloqué normalement.
		
		 m_cowboy2.Aut = Listint;
		 m_fantome2.Aut = Listint;
		 
		 EM.EM_add(m_fantome2);
		 EM.EM_add(m_cowboy2);
		
		 EM.afficher_EM();	 
		 List = EM.sort_affichage(); // valide jusqu'ICI
		 
		 
		 ArrayList<Entity> List_res2 = md.collision(m_cowboy2, List);
		 System.out.print("AFFICHAGE LIST2 : Collision entre cwboy2 et le reste (rocher)\n");
		 System.out.print("Taille liste res2 :\n"+List_res2.size()+ "\n");
		 for(int j=0; j<List_res2.size(); j++) {
			 System.out.print(List_res2.get(j).Name+"\n");
		 }
		 System.out.print("Fin affichage\n");
		 
		List_res2 = md.collision(m_fantome2, List);
		 System.out.print("AFFICHAGE LIST2 : Collision entre fantome2 et le reste\n");
		 System.out.print("Taille liste res2 :\n"+List_res2.size()+ "\n");
		 for(int j=0; j<List_res2.size(); j++) {
			 System.out.print(List_res2.get(j).Name+"\n");
		 }
		 System.out.print("Fin affichage\n");
		 
		 Mur m_Mur = new Mur(400,400,"mur1",100);
		 
		 
		 EM.EM_add(m_Mur);
		 EM.afficher_EM();
		 
		 List = EM.sort_affichage(); // valide jusqu'ICI
		 
		List_res2 = md.collision(m_fantome2, List);
		System.out.print("AFFICHAGE LIST2 : Collision entre fantome2 et le mur\n");
		System.out.print("Taille liste res2 :\n"+List_res2.size()+ "\n");
		for(int j=0; j<List_res2.size(); j++) {
			System.out.print(List_res2.get(j).Name+"\n");
		}
		System.out.print("Fin affichage\n");
			 
		 
		 
	}
}
