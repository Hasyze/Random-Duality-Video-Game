package info3.game;

import java.io.IOException;

public class Entity_Manager {
	 DynamicEntity[] Entities_Dynamic;
	// StaticEntity[] Entities_Static;
	 int offset_dynamic;
	 int offset_dead;

	 
	 
	 Entity_Manager(){
		 Entities_Dynamic = new DynamicEntity[1000];
		// Entities_Static= new StaticEntity[1000];
		 offset_dynamic = 0;
		 offset_dead = 0;
	 }
	 
	 void EM_add(Entity obj) {
		 entities[offset]=obj;
		 offset++;
		 
	 }
	 
	 void EM_remove(DynamicEntity obj){		 
		int i = EM_find(obj);
		if (i== -1) {
			System.out.print("ERREUR : cannot find entitiy in entity_manager ! ");
			return;
		}
		for (; i < offset_dynamic-1; i++) {
			Entities_Dynamic[i]=Entities_Dynamic[i+1];
			}
		 Entities_Dynamic[offset_dynamic]= null;
		 offset_dynamic--;
		//obj.detruire();
	 }
	 void EM_remove(StaticEntity obj) {
		 int i = EM_find(obj);
			if (i== -1) {
				System.out.print("ERREUR : cannot find entitiy in entity_manager ! ");
				return;
			}
			for (; i < offset_dynamic-1; i++) {
				Entities_Dynamic[i]=Entities_Dynamic[i+1];
				}
			 Entities_Dynamic[offset_dynamic]= null;
			 offset_dynamic--;
			//obj.detruire();
	 }
	 
	 int EM_find(Entity obj) {
		 for(int i =0; i< offset; i++) {
			if (entities[i]== obj) { 
				return i;
				}
		 	}
		return -1;		 
	 }
	 
	 void  eval() {
		
	 }
	 
}
