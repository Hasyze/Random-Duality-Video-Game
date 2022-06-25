package info3.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Entities.*;
import automaton.Type;

public class Modele {

	// Fait les collisions entre static et dynamique

	// Fait les collisions entre toutes les entités (dynamique/static)

	//

	Game game; // Pour set le changement de salle si le joueur rentre en collision avec une
				// porte

	public Modele(Game game) {
		this.game = game;
	}

	public double distance(int a_x, int a_y, int b_x, int b_y) {
		return Math.sqrt((a_x - b_x) * (a_x - b_x) + (a_y - b_y) * (a_y - b_y));
	}

	public int convertType(Type type) {
		switch (type) {
		case PLAYER:
			return 0;
		case ADVERSAIRE:
			return 1;
		case MISSILE:
			return 2;
		case CLUE:
			return 3;
		case VOID:
			return 4;
		case JUMPABLE:
			return 5;
		case OBSTACLE:
			return 6;
		case GATE:
			return 7;
		case DANGER:
			break;
		case NIMPORTE:
			break;
		case PRENDRE:
			break;
		case TEAM:
			break;
		default:
			break;
		}
		return -1;
	}
	
	public int vitessey(Entity Obj, Direction dir) {
        int speed = Obj.getSpeed();
        switch (dir) {
        case N:
            return -speed;
        case S:
            return speed;
        case NE:
            return -speed;
        case NW:
            return -speed;
        case SE:
            return speed;
        case SW:
            return speed;
        default:
            return 0;
        }
    }

    public int vitessex(Entity Obj, Direction dir) {
        int speed = Obj.getSpeed();
        switch (dir) {
        case W:
            return -speed;
        case E:
            return speed;
        case NE:
            return speed;
        case NW:
            return -speed;
        case SE:
            return speed;
        case SW:
            return -speed;
        default:
            return 0;
        }
    }

	public boolean collision(Entity Obj1, Entity Obj2, Direction Dir) {
		if (Obj1.getType() == Obj2.getType())
			return false;
		int X1 = Obj1.getHitbox().getAbscisse();
		int Y1 = Obj1.getHitbox().getOrdonnee();

		int X2 = Obj2.getHitbox().getAbscisse()+vitessex(Obj2,Obj2.direction);
		int Y2 = Obj2.getHitbox().getOrdonnee()+vitessey(Obj2,Obj2.direction);
		int speed = Obj1.getSpeed() + 1;
		switch (Dir) {
		case F:
			break;
		case NW:
			X1 -= speed;
			Y1 -= speed;
			break;
		case NE:
			X1 += speed;
			Y1 -= speed;
			break;
		case SW:
			X1 -= speed;
			Y1 += speed;
			break;
		case SE:
			X1 += speed;
			Y1 += speed;
			break;
		case W:
			X1 -= speed;
			break;
		case E:
			X1 += speed;
			break;
		case N:
			Y1 -= speed;
			break;
		case S:
			Y1 += speed;
			break;
		default:
			break;
		}
		int R1 = Obj1.getHitbox().getRayon();
		int R2 = Obj2.getHitbox().getRayon();
		return (distance(X1, Y1, X2, Y2) < R1 + R2);
	}
	public boolean collision(Entity Obj1, Entity Obj2) {
		if (Obj1.getType() == Obj2.getType())
			return false;
		int X1 = Obj1.getHitbox().getAbscisse()+vitessex(Obj1,Obj1.direction);
		int Y1 = Obj1.getHitbox().getOrdonnee()+vitessey(Obj1,Obj1.direction);

		int X2 = Obj2.getHitbox().getAbscisse()+vitessex(Obj2,Obj2.direction);
		int Y2 = Obj2.getHitbox().getOrdonnee()+vitessey(Obj2,Obj2.direction);

		int R1 = Obj1.getHitbox().getRayon();
		int R2 = Obj2.getHitbox().getRayon();
		return (distance(X1, Y1, X2, Y2) < R1 + R2);
	}
	
	public boolean inside(Rectangle R, Point P, int speedx1, int speedy1, int speedx2, int speedy2) {
		return (P.x + speedx1 >= R.x + speedx2 && P.x + speedx1 <= R.x + R.width + speedx2)
				&& (P.y + speedy1 >= R.y + speedy2 && P.y + speedy1 <= R.y + R.height + speedy2);
	}

	public boolean collisionR(Entity Obj1, Entity Obj2) {
		Rectangle R ;
		//if(type ==0)
			R= Obj1.getHitbox().getRect();
		/*else               /// POUR CLOSEST
			R= Obj1.getHitboxVoisin(dir).getRect();*/
		Point p1 = new Point(R.x, R.y);
		Point p2 = new Point(R.x + R.width, R.y);
		Point p3 = new Point(R.x, R.y + R.height);
		Point p4 = new Point(R.x + R.width, R.y + R.height);

		Rectangle r = Obj2.getHitbox().getRect();
		Point pa = new Point(r.x, r.y);
		Point pb = new Point(r.x + r.width, r.y);
		Point pc = new Point(r.x, r.y + r.height);
		Point pd = new Point(r.x + r.width, r.y + r.height);

		int speedx1 = vitessex(Obj1, Obj1.direction);
		int speedy1 = vitessey(Obj1, Obj1.direction);
		int speedx2 = vitessex(Obj2, Obj2.direction);
		int speedy2 = vitessey(Obj2, Obj2.direction);

		boolean b1 = inside(R, pa, speedx2, speedy2, speedx1, speedy1)
				|| inside(R, pb, speedx2, speedy2, speedx1, speedy1)
				|| inside(R, pc, speedx2, speedy2, speedx1, speedy1)
				|| inside(R, pd, speedx2, speedy2, speedx1, speedy1);

		boolean b2 = inside(r, p1, speedx1, speedy1, speedx2, speedy2)
				|| inside(r, p2, speedx1, speedy1, speedx2, speedy2)
				|| inside(r, p3, speedx1, speedy1, speedx2, speedy2)
				|| inside(r, p4, speedx1, speedy1, speedx2, speedy2);
		return b1 || b2;
	}
	
	public boolean collisionR(Entity Obj1, Entity Obj2, Direction dir/*, int type*/) {
		Rectangle R ;
		//if(type ==0)
			R= Obj1.getHitbox().getRect();
		/*else               /// POUR CLOSEST
			R= Obj1.getHitboxVoisin(dir).getRect();*/
		Point p1 = new Point(R.x, R.y);
		Point p2 = new Point(R.x + R.width, R.y);
		Point p3 = new Point(R.x, R.y + R.height);
		Point p4 = new Point(R.x + R.width, R.y + R.height);

		Rectangle r = Obj2.getHitbox().getRect();
		Point pa = new Point(r.x, r.y);
		Point pb = new Point(r.x + r.width, r.y);
		Point pc = new Point(r.x, r.y + r.height);
		Point pd = new Point(r.x + r.width, r.y + r.height);

		int speedx1 = vitessex(Obj1, dir);
		int speedy1 = vitessey(Obj1, dir);
		int speedx2 = vitessex(Obj2, Obj2.direction);
		int speedy2 = vitessey(Obj2, Obj2.direction);

		boolean b1 = inside(R, pa, speedx2, speedy2, speedx1, speedy1)
				|| inside(R, pb, speedx2, speedy2, speedx1, speedy1)
				|| inside(R, pc, speedx2, speedy2, speedx1, speedy1)
				|| inside(R, pd, speedx2, speedy2, speedx1, speedy1);

		boolean b2 = inside(r, p1, speedx1, speedy1, speedx2, speedy2)
				|| inside(r, p2, speedx1, speedy1, speedx2, speedy2)
				|| inside(r, p3, speedx1, speedy1, speedx2, speedy2)
				|| inside(r, p4, speedx1, speedy1, speedx2, speedy2);
		return b1 || b2;
	}


	public ArrayList<Entity> collision(Entity Obj, ArrayList<Entity> list, Direction dir) {
		ArrayList<Entity> col = new ArrayList<Entity>();
		boolean collision = false;

		for (int i = 0; i < list.size(); i++) {
			Entity elem = list.get(i); // on récupére un a un les elements de la liste
			collision = collisionR(Obj, elem, dir); // calcul de la collision entre l'objet et l'élément de la liste.

			int type = elem.getType(); // retourne le type de l'élément dans la liste :

			/**
			 * Type 0: Joueur 1: Ennemi 2: Missile Ennemi 3: Missile Joeur 4: Fnatome 5:
			 * Rocher 6: Mur 7: Porte
			 **/

			switch (Obj.getType()) { // en fonction du type de l'obj, on fais le cas différent des collisions si il
										// y a
										// ex : fantome : il ne collisionne pas, sauf avec les portes et les murs.
			case 0: // Joueur
				if (collision && (type == 1 || type == 2 || type == 5 || type == 6 || type == 7)) {
					// System.out.print("collision \n");
					if ((type == 7) && ((Porte) elem).etat) {
						System.out.print("collision avec porte ouverte");
						//game.changement_de_salle = (Porte) elem;
					}
					col.add(elem);
				}
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

	public void interaction(Entity Obj, ArrayList<Entity> list) {
		for (int i = 0; i < list.size(); i++) {
			Entity elem = list.get(i);
			int type = elem.getType();
			switch (Obj.getType()) {
			case 0:
				if (type == 1 || type == 2) {
					Obj.degatVie(1);
					if (type == 2)
						elem.degatVie(1);
				}
				break;
			case 1:
				if (type == 3)
					Obj.degatVie(1);
				else if (type == 0)
					elem.degatVie(1);
				break;
			case 2:
				if (type == 0 || type == 3) {
					Obj.degatVie(1);
					elem.degatVie(1);
				}
				break;
			case 3:
				if (type == 1 || type == 2) {
					Obj.degatVie(1);
					elem.degatVie(1);
				}
				break;
			case 4:
				break;
			case 7:
				/*
				 * if(Obj.type==1) list.deplace();
				 */
				break;
			}
		}
	}
	
	public void interaction(Entity Obj, Entity Obj2) {
			int type = Obj2.getType();
			switch (Obj.getType()) {
			case 0:
				if (type == 1 || type == 2) {
					Obj.degatVie(1);
					System.out.println(Obj.getvie());
					if (type == 2)
						Obj2.degatVie(1);
				}
				if(type == 7) {
					game.changement_de_salle = (Porte)Obj2;
				}
				break;
			case 1:
				if (type == 3)
					Obj.degatVie(1);
				else if (type == 0)
					Obj2.degatVie(1);
				break;
			case 2:
				if (type == 0 || type == 3) {
					Obj.degatVie(1);
					Obj2.degatVie(1);
				}
				break;
			case 3:
				if (type == 1 || type == 2) {
					Obj.degatVie(1);
					Obj2.degatVie(1);
				}
				break;
			case 4:
				break;
			case 7:
				/*
				 * if(Obj.type==1) list.deplace();
				 */
				break;
			}
		}
	
	
	public void collionsDynamic(List<Entity> liste) {
		for(int i = 0; i<liste.size()-1; i++) {
			for(int j = i+1; j<liste.size(); j++) {
				if(collisionR(liste.get(i),liste.get(j))) {
					System.out.println(liste.get(i).Name+"|"+liste.get(j).Name+"\n"+liste.get(i).getType()+"|"+liste.get(j).getType());
					interaction(liste.get(i),liste.get(j));
				}
				
			}
		}
	}

	public boolean collisions(Entity Obj, ArrayList<Entity> list, Direction dir, Type type) {
		for (int i = 0; i < list.size(); i++) {
			Entity elem = list.get(i);
			if (elem.getType() == convertType(type)) {
				if (collisionR(Obj, elem, dir))
					return true;
			}
		}
		return false;
	}
}
