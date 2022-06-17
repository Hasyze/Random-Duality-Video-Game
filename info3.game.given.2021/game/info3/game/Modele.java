package info3.game;

import java.util.ArrayList;

import Entities.*;

public class Modele {

	// Fait les collisions entre static et dynamique

	// Fait les collisions entre toutes les entités (dynamique/static)

	//

	EntityManager EM;

	public Modele(EntityManager EM) {
		this.EM = EM;
	}

	public double distance(int a_x, int a_y, int b_x, int b_y) {
		return Math.sqrt((a_x - b_x) * (a_x - b_x) + (a_y - b_y) * (a_y - b_y));
	}

	boolean collision(Entity Obj1, Entity Obj2) {
		if (Obj1.getType() == Obj2.getType())
			return false;
		int X1 = Obj1.getHitbox().getAbscisse() + Obj1.getx_speed() - Obj1.getx_nspeed();
		int Y1 = Obj1.getHitbox().getOrdonnee() + Obj1.gety_speed() - Obj1.gety_nspeed();

		int X2 = Obj2.getHitbox().getAbscisse() + Obj2.getx_speed() - Obj2.getx_nspeed();
		int Y2 = Obj2.getHitbox().getOrdonnee() + Obj2.gety_speed() - Obj2.gety_nspeed();

		int R1 = Obj1.getHitbox().getRayon();
		int R2 = Obj2.getHitbox().getRayon();

		return distance(X1, Y1, X2, Y2) < R1 + R2;
	}

	public ArrayList<Entity> collision(Entity Obj, ArrayList<Entity> list) {
		ArrayList<Entity> col = null;
		boolean collision = false;
		for (int i = 0; i < list.size(); i++) {
			Entity elem = list.get(i);
			collision = collision(Obj, elem);
			int type = elem.getType();
			switch (Obj.getType()) {
			case 0: // Joueur
				if (collision && (type == 1 || type == 2 || type == 5 || type == 6 || type == 7))
					col.add(elem);
			case 1: // Ennemi
				if (collision && (type==0 ||type == 3 || type == 5 || type == 6 || type == 7))
					col.add(elem);
			case 2: // Missile Ennemi
				if (collision && (type == 0 || type == 3 || type == 5 || type == 6 || type == 7))
					col.add(elem);

			case 3: // Missile Joueur
				if (collision && (type == 1 || type == 2 || type == 5 || type == 6 || type == 7))
					col.add(elem);

			case 4: // Fantome
				if (collision && (type == 6 || type == 7))
					col.add(elem);

			}
		}
		return col;
	}

	public void eval() {
		ArrayList<Entity> Static = EM.getStatic();
		ArrayList<Entity> Dynamic = EM.getDynamic();
		for (int i = 0; i < Dynamic.size(); i++) {
			Entity Obj1 = Dynamic.get(i);
			for (int j = 0; j < Dynamic.size(); j++) {
				if (!collision(Obj1, Dynamic.get(j)))
					Obj1.move();
				else if (!Dynamic.get(j).getClass().equals(Obj1.getClass())) { // On ne vérifie pas la collision d'un
																				// objet avec lui-même
					String classObj = Obj1.getClass().getName();
					String classD = Dynamic.get(j).getClass().getName();
					switch (classObj) {
					case "Cowboy":
						if (classD == "Ennemi") {
							Obj1.degatVie(1);
							Obj1.stop();
						} else if (classD = "Missile" && ((Missile) Dynamic.get(j)).type == 1) {
							Obj1.degatVie(1);
							Dynamic.get(j).dispear();
						} else
							Obj1.move();
						break;
					case "Ennemi":
						if (classD == "Cowboy") {
							Dynamic.get(j).gedatVie(1);
							Dynamic.get(j).stop();
						} else if (classD == "Missile" && ((Missile) Dynamic.get(j)).type == 0) {
							Obj1.degatVie(1);
							Dynamic.get(j).dispear();
						} else
							Obj1.move();
						break;
					case "Missile":
						if (classD == "Cowboy" && ((Missile) Obj1).type == 1) {
							Dynamic.get(j).gedatVie(1);
							Obj1.disapear();
						} else if (classD == "Ennemi" && ((Missile) Obj1).type == 0) {
							Dynamic.get(j).gedatVie(1);
							Obj1.disapear();
						} else if (classD == "Missile" && ((Missile) Obj1).type != ((Missile) Dynamic.get(j)).type) {
							Obj1.disapear();
							Dynamic.get(j).dispear();
						}
						break;

					}
				}
			}
			for (int k = 0; k < Satic.size(); k++) {
				if (Static.get(k) instanceof Porte && Obj1 instanceof Cowboy) {
					if (Dynamic.size() == 2) { // il reste que 2 joueur sur la map
						if (collision(Obj1, Static.get(k)))
							Obj1.teleportation();
					}
				}
				if (!collision(Obj1, Static.get(k)))
					Obj1.move();
				else
					Obj1.stop();
			}
		}
	}

}
