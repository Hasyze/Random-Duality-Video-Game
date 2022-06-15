package info3.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Entity_Manager {
	 protected ArrayList<Entity> Dynamic;
	 protected ArrayList<Entity> Static;
	 int nb_dynamic;
	 int nb_static;

	 
	 
	 Entity_Manager(){
		 Dynamic = new ArrayList<Entity>();;
		 Static = new ArrayList<Entity>();
	   	 nb_dynamic = 0;
		 nb_static = 0;
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
	 
	 
//////////////////////////////////
/////////////////////////////////Remove est private car seulement appelé lors de eval de EM, a part EM personne
	 						   // n'a le droit de supprimmer des entitées. Return 1 si réussi, 0 sinon.
	 /*private int EM_remove(Entity obj){		 
		 if (Dynamic.remove(obj)) {
			 return 1;
		 }else if (Static.remove(obj)) {
			 return 1;
		 }else {
			 System.out.print("ERREUR REMOVE, ni static ni dynamic : "+ obj);
			 return 0;
		 }
	 }*/ // OLD VERSION DE REMOVE, arraylist fait ça très bien tout seul.
	 
	public void organize() { // regard toutes les entitées dynamiques, si leur flag vie = 0, alors elles dégagent car elles sont mortes.
							 // les static ne peuvent pas mourir car leur automate est nul => pas de concept de vie 
		for(int i=0; i<Dynamic.size(); i++) {
			if(Dynamic.get(i).Vie ==0){ //mort : ça dégage
				Dynamic.remove(i);
			}
		}
	}
	 
	 
	 
	 
	public void tick(DynamicEntity Obj1,long elapsed) {
		ArrayList<Entity> New_Dynamic = new ArrayList<Entity>();
		ArrayList<Entity> New_Static = new ArrayList<Entity>();
		
		Obj1.m_imageElapsed += elapsed;
		if (Obj1.m_imageElapsed > 200) {
			Obj1.m_imageElapsed = 0;
			// m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}
	
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