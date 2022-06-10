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
         if( obj instanceof DynamicEntity) {
             Entities_Dynamic[offset_dynamic]=(DynamicEntity) obj;
             offset_dynamic++;
         }
         /*else if ( obj instanceof StaticEntity){
             entities_static[offset]=(StaticEntity) obj;
             offset_static++;
             }*/
         
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
	/* void EM_remove(StaticEntity obj) {
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
	 }*/
	 
	
	
 int EM_find(DynamicEntity obj) {
         for(int i =0; i< offset_dynamic; i++) {
            if (Entities_Dynamic[i]== obj) { 
                return i;
                }
             }
        return -1;         
     }
     /*int EM_find(StaticEntity obj) {
         for(int i =0; i< offset_static; i++) {
            if (entities_static[i]== obj) { 
                return i;
                }
             }
        return -1;         
     }*/
 	Boolean collision_test(DynamicEntity Obj1, DynamicEntity Obj2) {
 		//TODO Aziz c'est ici les test de collision, tu renvoies true si oui il y a collision, false sinon
 		return false;
 	}
 
 	void eval() {
 		Entity_Manager L2 = new Entity_Manager();
 		
 		for(int i=0; i < offset_dynamic; i++) {
 			for (int j=0; j< offset_dynamic; j++) {
 				if (collision_test(this.Entities_Dynamic[i],this.Entities_Dynamic[j])) {
 					//
 				}
 			}
 		}
 		
 	}
 
 
 
 
 
 
 
 
 
 
 
}
