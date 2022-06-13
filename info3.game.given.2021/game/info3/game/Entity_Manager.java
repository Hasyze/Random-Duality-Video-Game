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
 	boolean collision_test(DynamicEntity Obj1, DynamicEntity Obj2) {
 		//TODO Aziz c'est ici les test de collision, tu renvoies true si oui il y a collision, false sinon
 		if (Obj1.getClass().equals(Obj2.getClass()) ) {
 			return false;
 		}
 		if (Obj1 instanceof Hero) {
 			/*if (Obj2 instanceof Hero) {
 				return false;
 			}
 			else*/ if(Obj2 instanceof Enemy) {
 				return Obj1.hitbox.collision(Obj2);
 			}
 			else if(Obj2 instanceof ProjectileE){
 				return Obj1.hitbox.collision(Obj2);
 			}else {
 				return Obj1.hitbox.collision(Obj2);
 			}
 		}
 		else if(Obj1 instanceof Enemy) {
 			if (Obj2 instanceof Hero) {
 				return Obj1.hitbox.collision(Obj2);
 			}
 			/*else if(Obj2 instanceof Enemy) {
 				return false;
 			}*/
 			else if(Obj2 instanceof ProjectileE){
 				return Obj1.hitbox.collision(Obj2);
 			}else {
 				
 			}
 		}else if (Obj1 instanceof ProjectileE){ //Projectile Enemy
 			if (Obj2 instanceof Hero) {
 				return Obj1.hitbox.collision(Obj2);
 			}
 			else if(Obj2 instanceof Enemy) {
 				return Obj1.hitbox.collision(Obj2);
 			}
 			/*else if(Obj2 instanceof ProjectileE){
 				return false;*/
 			}else {
 				
 			}
 		}else { //Projectile Hero
 			if (Obj2 instanceof Hero) {
 				return Obj1.hitbox.collision(Obj2);
 			}
 			else if(Obj2 instanceof Enemy) {
 				return Obj1.hitbox.collision(Obj2);
 			}
 			else if(Obj2 instanceof ProjectileE){
 				return Obj1.hitbox.collision(Obj2);
 			}/*else {
 				return false;
 			}*/
 		}
 		
	boolean collision_test(DynamicEntity Obj1, StaticEntity Obj2) { 
		return Obj1.hitbox.collision(Obj2);
	}
 	void eval() {
 		Entity_Manager L2 = new Entity_Manager();
 		
 		for(int i=0; i < offset_dynamic; i++) {
 			for (int j=i; j< offset_dynamic; j++) {
 				if (collision_test(this.Entities_Dynamic[i],this.Entities_Dynamic[j])) {
 					//ici on appelle l'automate de comportement en cas de collision 
 					// ex collision entre balle et mur : automate correspondant, 
 					//ça nous permet de créer n'importe quoi sur les détéctions de collisions
 					// ex : si on se fait toucher, c'est l'ennemi qui prend un dps, etc..
 					
 			for(int k = 0; k<offset_static; k ++ ) {
 				if (collision_test(this.Entities_Static[i],this.Entities_Static[k])) {
 					
 				}
 			}
 					
 					
 					
 					
 				}
 			}
 		}
 		
 	}
 
}