package info3.game;

import java.util.ArrayList;

import Entities.*;

public class Modele {

	// Fait les collisions entre static et dynamique

	// Fait les collisions entre toutes les entités (dynamique/static)

	//
		
	public Modele() {

	}

	public double distance(int a_x, int a_y, int b_x, int b_y) {
		return Math.sqrt((a_x - b_x) * (a_x - b_x) + (a_y - b_y) * (a_y - b_y));
	}

	public boolean collision(Entity Obj1, Entity Obj2) {
		if (Obj1.getType() == Obj2.getType())
			return false;
		int X1 = Obj1.getx() + Obj1.getx_speed() - Obj1.getx_nspeed();
		int Y1 = Obj1.gety() + Obj1.gety_speed() - Obj1.gety_nspeed();

		int X2 = Obj2.getx() + Obj2.getx_speed() - Obj2.getx_nspeed();
		int Y2 = Obj2.gety() + Obj2.gety_speed() - Obj2.gety_nspeed();

		int R1 = Obj1.getHitbox().getRayon();
		int R2 = Obj2.getHitbox().getRayon();

		return distance(X1, Y1, X2, Y2) < R1 + R2;
	}

	public ArrayList<Entity> collision(Entity Obj, ArrayList<Entity> list) {
		ArrayList<Entity> col = new ArrayList<Entity>();
		boolean collision = false;

		for (int i = 0; i < list.size(); i++) {
			Entity elem = list.get(i); // on récupére un a un les elements de la liste
			collision = collision(Obj, elem); // calcul de la collision entre l'objet et l'élément de la liste.

			int type = elem.getType(); // retourne le type de l'élément dans la liste :

			/**
			 * Type 0: Joueur 1: Ennemi 2: Missile Ennemi 3: Missile Joeur 4: Fnatome 5:
			 * Rocher 6: Mur 7: Porte
			 **/

			switch (Obj.getType()) { // en fonction du type de l'obj, on fais le cas différent des collisions si il
										// y a
										// ex : fantome : il ne collisionne pas, sauf avec les portes et les murs.
			case 0: // Joueur
				if (collision && (type == 1 || type == 2 || type == 5 || type == 6 || type == 7))
					col.add(elem);
				break;
			case 1: // Ennemi
				if (collision && (type == 0 || type == 3 || type == 5 || type == 6 || type == 7))
					col.add(elem);
				break;
			case 2: // Missile Ennemi
				if (collision && (type == 0 || type == 3 || type == 5 || type == 6 || type == 7))
					col.add(elem);
				break;
			case 3: // Missile Joueur
				if (collision && (type == 1 || type == 2 || type == 5 || type == 6 || type == 7))
					col.add(elem);
				break;
			case 4: // Fantome
				if (collision && (type == 6 || type == 7))
					col.add(elem);
				break;
			}
		}
		return col;
	}

	public boolean collisions(Entity Obj, ArrayList<Entity> list) {
		for (int i = 0; i < list.size(); i++) {
			Entity elem = list.get(i); // on récupére un a un les elements de la liste
			if (collision(Obj, elem))
				return true;
		}
		return false;
	}
}
