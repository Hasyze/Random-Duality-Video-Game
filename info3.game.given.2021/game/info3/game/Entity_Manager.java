package info3.game;

import java.io.IOException;

public class Entity_Manager {
	 Entity[] entities;
	 int offset;

	 
	 
	 Entity_Manager(){
		 entities = new Entity[1000];
		 offset = 0;
	 }
	 
	 void EM_add(Entity obj) {
		 entities[offset]=obj;
		 offset++;
		 
	 }
	 
	 void EM_remove(Entity obj){		 
		int i = EM_find(obj);
		if (i== -1) {
			System.out.print("ERREUR : cannot find entitiy in entity_manager ! ");
			//on peut metre une io interruption ici.
		}
		for (; i < offset-1; i++) {
			entities[i]=entities[i+1];
			}
		 entities[offset]= null;
		 offset--;
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
	 
}
