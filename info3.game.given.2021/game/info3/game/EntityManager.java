package info3.game;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



import Entities.Entity;


public class EntityManager {
	 protected ArrayList<Entity> Dynamic;
	 protected ArrayList<Entity> Static;
	  
	 
	
	 EntityManager(){
		 Dynamic = new ArrayList<Entity>();
		 Static = new ArrayList<Entity>();
	  
	 } 
	 
	 public ArrayList<Entity> getDynamic(){
		 return Dynamic;
	 }
	 public ArrayList<Entity> getStatic(){
		 return Static;
	 }
	 
////////////////////////////////// ADD	
	 //return 1 si réussi, 0 sinon
	 public int EM_add(Entity obj) {     
        /* if (obj.Aut == null) {
        	 // alors il est static;
        	 Static.add(obj);
        	 return 1;
         }
         else if(obj.Aut != null) {
        	 //alors il est dynamic;
        	 Dynamic.add(obj);
        	 return 1;
       }else {
    	   System.out.print("ERREUR ADD, ni static ni dynamic : "+ obj);
    	   return 0;
       }*/
		 
		 Dynamic.add(obj);
		 return 1;
         
     }
	 
	 
	 


	protected void organize() { // regarde toutes les entitées dynamiques, si leur flag vie = 0, alors elles dégagent car elles sont mortes.
							    // les static ne peuvent pas mourir car leur automate est nul => pas de concept de vie 
		//System.out.print("Dynamic : size "+Dynamic.size()+"\n");
		for(int i=0; i<Dynamic.size(); i++) {
			//System.out.print("Vie de "+Dynamic.get(i).Name+" : "+ Dynamic.get(i).getvie()+"\n");
			if(Dynamic.get(i).getvie()==0){ //mort : ça dégage
				Dynamic.remove(i);
				i--;
			}
		}
	}


	public ArrayList<Entity> sort_affichage() {
		ArrayList<Entity> New_List = new ArrayList<Entity>();
		New_List.addAll(Dynamic);
		New_List.addAll(Static);
		
		Collections.sort(New_List, new Comparator<Entity>() {

			@Override
			public int compare(Entity o1, Entity o2) {
				return Integer.compare(o1.gety(), o2.gety());	
			}
		});		
		return New_List;		
		}
	
	//stocks a list of entities into a list of dyn and static appart.
	public void list_to_EM(ArrayList<Entity> List) {
		for (int i =0; i< List.size(); i++) {
			if (List.get(i).Aut == null) {
	        	 // alors il est static;
	        	 Static.add(List.get(i));     	 
	         }
	         else if(List.get(i).Aut != null) {
	        	 //alors il est dynamic;
	        	 Dynamic.add(List.get(i));
	        }
		}
	}
	public void afficher_EM(){
		System.out.print(" ENTITY MANAGER AFFICHAGE : \n");
		System.out.print(" DYNAMIC LIST : \n");
		for(int i=0; i < Dynamic.size(); i++) {
			System.out.print(Dynamic.get(i).Name + "\n");
		}
		System.out.print("\n");
		System.out.print(" Static LIST : \n");
		for(int j=0; j < Static.size(); j++) {
			System.out.print(Static.get(j).Name + "\n");
		}
		System.out.print("\n");
		System.out.print("\n");
		
		
	}
	
	
	
	public void tick(long elapsed/*Entity Obj1,
					ArrayList<Entity> List,
					long elapsed // si jamais on utilise une list*/
	 				) {
		ArrayList<Entity> New_Dynamic = new ArrayList<Entity>();
		ArrayList<Entity> New_Static = new ArrayList<Entity>();
		
		/*Obj1.m_imageElapsed += elapsed;
		if (Obj1.m_imageElapsed > 200) {
			Obj1.m_imageElapsed = 0;
			// m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}*/// a metre ailleurs mais je sais pas ou.
	
		for(int i =0; i< Dynamic.size(); i++) { // seulement les dynamic font un step;
			Entity e = Dynamic.get(i);
			e.step(New_Dynamic, New_Static); // on ajoute pour les créations qui se font dans les nouvelles listes.
			e.tick(elapsed);
		}
		organize(); // vire les morts
		
		New_Dynamic.addAll(Dynamic); // ya tout !
		New_Static.addAll(Static);   // ya tout v2!
		
		this.Dynamic = New_Dynamic;
		this.Static = New_Static;
		
	}
	 
		
	
 
 
}