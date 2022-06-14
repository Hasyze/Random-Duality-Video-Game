package info3.game;

import java.io.IOException;

public class Entity_Manager {
	DynamicEntity[] Entities_Dynamic;
	StaticEntity[] Entities_Static;
	int offset_dynamic;
	int offset_static;

	public Entity_Manager() {
		Entities_Dynamic = new DynamicEntity[1000];
		Entities_Static = new StaticEntity[1000];
		offset_dynamic = 0;
		offset_static = 0;
	}

	void EM_add(Entity obj) {
		if (obj instanceof DynamicEntity) {
			Entities_Dynamic[offset_dynamic] = (DynamicEntity) obj;
			offset_dynamic++;
		} else if (obj instanceof StaticEntity) {
			Entities_Static[offset_static] = (StaticEntity) obj;
			offset_static++;
		}

	}

	void EM_remove(DynamicEntity obj) {
		int i = EM_find(obj);
		if (i == -1) {
			System.out.print("ERREUR : cannot find entitiy in entity_manager ! ");
			return;
		}
		for (; i < offset_dynamic - 1; i++) {
			Entities_Dynamic[i] = Entities_Dynamic[i + 1];
		}
		Entities_Dynamic[offset_dynamic] = null;
		offset_dynamic--;
		// obj.detruire();
	}

	void EM_remove(StaticEntity obj) {
		int i = EM_find(obj);
		if (i == -1) {
			System.out.print("ERREUR : cannot find entitiy in entity_manager ! ");
			return;
		}
		for (; i < offset_dynamic - 1; i++) {
			Entities_Dynamic[i] = Entities_Dynamic[i + 1];
		}
		Entities_Dynamic[offset_dynamic] = null;
		offset_dynamic--;
		// obj.detruire();
	}

	int EM_find(DynamicEntity obj) {
		for (int i = 0; i < offset_dynamic; i++) {
			if (Entities_Dynamic[i] == obj) {
				return i;
			}
		}
		return -1;
	}

	int EM_find(StaticEntity obj) {
		for (int i = 0; i < offset_static; i++) {
			if (Entities_Static[i] == obj) {
				return i;
			}
		}
		return -1;
	}

	boolean collision_test(DynamicEntity Obj1, DynamicEntity Obj2) {
		// Aziz c'est ici les test de collision, tu renvoies true si oui il y a
		// collision, false sinon
		if (Obj1.getClass().equals(Obj2.getClass())) {
			return false;
		}
		return Obj1.hitbox.collision(Obj2);
	}

	boolean collision_test(DynamicEntity Obj1, StaticEntity Obj2) {
		return Obj1.hitbox.collision(Obj2);
	}

	boolean collision_test(DynamicEntity Obj1) {

		for (int i = 0; i < offset_dynamic; i++) {
			if (Obj1 != Entities_Dynamic[i])
				if (Entities_Dynamic[i].hitbox.verif(Obj1))
					return true;
		}
		for (int j = 0; j < offset_static; j++) {
			if ( Entities_Static[j].hitbox.verif(Obj1))
				return true;
		}
		return false;
	}

	public DynamicEntity trouver_min() {
		DynamicEntity obj = Entities_Dynamic[0];
		int min = obj.y;
		for (int i = 1; i < offset_dynamic; i++) {
			if (Entities_Dynamic[i].y < min) {
				min = Entities_Dynamic[i].y;
				obj = Entities_Dynamic[i];
			}
		}
		return obj;
	}

	public void update() {
		DynamicEntity[] nouvelle = new DynamicEntity[1000];
		DynamicEntity obj;
		int len = offset_dynamic;
		for (int i = 0; i < len; i++) {
			obj = trouver_min();
			nouvelle[i] = obj;
			EM_remove(obj);
		}
		Entities_Dynamic = nouvelle;
	}

	public void tick(DynamicEntity Obj1, long elapsed) {
		Obj1.m_imageElapsed += elapsed;
		if (Obj1.m_imageElapsed > 200) {
			Obj1.m_imageElapsed = 0;
			// m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}
		Obj1.m_moveElapsed += elapsed;
		Obj1.hitbox = new Hitbox(25, Obj1.x, Obj1.y);
		if (Obj1.m_moveElapsed > 24 & Obj1.m_width != 0 && !collision_test(Obj1)) {
			Obj1.m_moveElapsed = 0;
			Obj1.x = (Obj1.x + Obj1.x_speed - Obj1.x_nspeed) % Obj1.m_width;
			Obj1.y = (Obj1.y + Obj1.y_speed - Obj1.y_nspeed) % Obj1.m_width;
			Obj1.set_orientation();
		}
	}

	void eval() {
		Entity_Manager L2 = new Entity_Manager();

		for (int i = 0; i < offset_dynamic; i++) {
			for (int j = i; j < offset_dynamic; j++) {
				if (collision_test(this.Entities_Dynamic[i], this.Entities_Dynamic[j])) { // comparaisons dyn dyn
					// ici on appelle l'automate de comportement en cas de collision
					// ex collision entre balle et mur : automate correspondant,
					// ça nous permet de créer n'importe quoi sur les détéctions de collisions
					// ex : si on se fait toucher, c'est l'ennemi qui prend un dps, etc..

					for (int k = 0; k < offset_static; k++) {
						if (collision_test(this.Entities_Dynamic[i], this.Entities_Static[k])) { // comparaisons dyn
																									// stat

							// ici on appelle l'automate
						}
					}
					// après tout les calculs, on réorganise ici la liste et on en fait la liste
					// d'netity manager.

				}
			}
		}

	}

}