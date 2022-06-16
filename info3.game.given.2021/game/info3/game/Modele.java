package info3.game;

public class Modele {
	public Entity_Manager EM;
	public Etage floor;
	
	Modele (Entity_Manager EM,Etage e){
		this.EM = EM;
		floor = e;
	}
	
	boolean collision_test() {
		for (int i=0; i<EM.offset_dynamic; i++) {
			Entity Obj1=EM.Entities_Dynamic[i];
			for(int j= 0; j<EM.offset_dynamic;j++) {
				if(EM.Entities_Dynamic[j]!=Obj1) { //On ne vérifie pas la collision d'un objet avec lui-même
					if(Obj1.hitbox.verif_collision(EM.Entities_Dynamic[j]))
						return true;
				}
			}
			for(int k=0; k<EM.offset_static;k++) {
				if(Obj1.hitbox.verif_collision(EM.Entities_Static[k]))
					return true;	
			}
		}
		return false;
 	}
	
	boolean collision_test(Entity Obj1) {
		for (int i=0; i<EM.offset_dynamic; i++) {
			for(int j= 0; j<EM.offset_dynamic;j++) {
				if(EM.Entities_Dynamic[j]!=Obj1) { //On ne vérifie pas la collision d'un objet avec lui-même
					if(Obj1.hitbox.verif_collision(EM.Entities_Dynamic[j]))
						return true;
				}
			}
			for(int k=0; k<EM.offset_static;k++) {
				if(Obj1.hitbox.verif_collision(EM.Entities_Static[k]))
					return true;	
			}
		}
		return false;
 	}
	
	boolean test_move (Entity Obj1, long elapsed) {
		if (!collision_test(Obj1)) {
			EM.tick(Obj1,elapsed);
			return true;
		}
		return false;
	}
	public DynamicEntity trouver_min() {
		DynamicEntity obj = EM.Entities_Dynamic[0];
		int min = obj.y;
		for (int i = 1; i < EM.offset_dynamic; i++) {
			if (EM.Entities_Dynamic[i].y < min) {
				min = EM.Entities_Dynamic[i].y;
				obj = EM.Entities_Dynamic[i];
			}
		}
		return obj;
	}

	public void update() {
		DynamicEntity[] nouvelle = new DynamicEntity[1000];
		DynamicEntity obj;
		int len = EM.offset_dynamic;
		for (int i = 0; i < len; i++) {
			obj = trouver_min();
			nouvelle[i] = obj;
			EM.EM_remove(obj);
		}
		EM.Entities_Dynamic = nouvelle;
	}

	
}
