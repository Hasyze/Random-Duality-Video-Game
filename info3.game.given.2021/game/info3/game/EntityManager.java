package info3.game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Entities.Entity;


public class EntityManager {
	 protected ArrayList<Entity> Dynamic;
	 protected ArrayList<Entity> Static;
	  
	 
	 EntityManager(){
		 Dynamic = new ArrayList<Entity>();;
		 Static = new ArrayList<Entity>();
	  
	 }
	 
	 
////////////////////////////////// ADD	
	 //return 1 si réussi, 0 sinon
	 int EM_add(Entity obj) {     
         if (obj.Aut == null) {
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
       }
         
     }
	 
	 


	protected void organize() { // regarde toutes les entitées dynamiques, si leur flag vie = 0, alors elles dégagent car elles sont mortes.
							 // les static ne peuvent pas mourir car leur automate est nul => pas de concept de vie 
		for(int i=0; i<Dynamic.size(); i++) {
			if(Dynamic.get(i).getvie()==0){ //mort : ça dégage
				Dynamic.remove(i);
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
	 
	 
	public void tick(/*Entity Obj1,*/long elapsed) {
		ArrayList<Entity> New_Dynamic = new ArrayList<Entity>();
		ArrayList<Entity> New_Static = new ArrayList<Entity>();
		
		/*Obj1.m_imageElapsed += elapsed;
		if (Obj1.m_imageElapsed > 200) {
			Obj1.m_imageElapsed = 0;
			// m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}*/// a metre ailleurs mais je sais pas ou.
	
		for(int i =0; i< Dynamic.size(); i++) { // seulement les dynamic font un step;
			Dynamic.get(i).step(New_Dynamic, New_Static); // on ajoute pour les créations qui se font dans les nouvelles listes.
		}
		organize(); // vire les morts
		
		New_Dynamic.addAll(Dynamic); // ya tout !
		New_Static.addAll(Static);   // ya tout v2!
		
		this.Dynamic = New_Dynamic;
		this.Static = New_Static;
		
	}
	 
		
	
 
 
}