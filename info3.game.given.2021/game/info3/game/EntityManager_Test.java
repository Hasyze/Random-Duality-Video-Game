package info3.game;


import Entities.Cowboy;

public class EntityManager_Test {

	public static void main(String args[]) throws Exception {
		
		System.out.print("Début des tests\n");
		 EntityManager EM = new EntityManager();;
		 Modele model = new Modele();
		 
		 Cowboy m_cowboy1 = new Cowboy(model,"obj1");
		 Cowboy m_cowboy2 = new Cowboy(model,"Obj2");
		 Cowboy m_cowboy3 = new Cowboy(model,"Obj3");
		 
		 Cowboy[] List = {m_cowboy1,m_cowboy2,m_cowboy3};
		 int i;
		 int[] Listint = {1,2,3}; // un faux automate, surtout non nul quoi;
		 
		 
		 EM.EM_add(m_cowboy1);
			EM.tick();		 
		 EM.afficher_EM();
		 
 		 m_cowboy2.Aut = Listint;
		 EM.EM_add(m_cowboy2);
			EM.tick();	
		 EM.afficher_EM();
		 
		m_cowboy2.setVie(-1);
		EM.tick();
		
		EM.afficher_EM();
		
		

		 /*
		 while(true) {;
		 i =(int)Math.random()*(3-1+1)+1;
		 // Math.random() * (max - min + 1) + min  
		 EM.EM_add(List[i]);
		 
		 } */
	}
	
		
	
	 
		 
}
