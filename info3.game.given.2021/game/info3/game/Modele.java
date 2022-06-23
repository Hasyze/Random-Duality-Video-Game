package info3.game;

import java.awt.Point;
import java.awt.Rectangle;
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
		int X1 = Obj1.getHitbox().getAbscisse() + Obj1.getx_speed() - Obj1.getx_nspeed();
		int Y1 = Obj1.getHitbox().getOrdonnee() + Obj1.gety_speed() - Obj1.gety_nspeed();

		int X2 = Obj2.getHitbox().getAbscisse() + Obj2.getx_speed() - Obj2.getx_nspeed();
		int Y2 = Obj2.getHitbox().getOrdonnee() + Obj2.gety_speed() - Obj2.gety_nspeed();

		int R1 = Obj1.getHitbox().getRayon();
		int R2 = Obj2.getHitbox().getRayon();

		return distance(X1, Y1, X2, Y2) < R1 + R2;
	}

	public boolean inside(Rectangle R, Point P, int speedx1, int speedy1, int speedx2, int speedy2) {
		return (P.x + speedx1 >= R.x + speedx2 && P.x + speedx1 <= R.x + R.width + speedx2)
				&& (P.y + speedy1 >= R.y + speedy2 && P.y + speedy1 <= R.y + R.height + speedy2);
	}

	public boolean collisionR(Entity Obj1, Entity Obj2) {
		Rectangle R = Obj1.getHitbox().getRect();
		Point p1 = new Point(R.x, R.y);
		Point p2 = new Point(R.x + R.width, R.y);
		Point p3 = new Point(R.x, R.y + R.height);
		Point p4 = new Point(R.x + R.width, R.y + R.height);

		Rectangle r = Obj2.getHitbox().getRect();
		Point pa = new Point(r.x, r.y);
		Point pb = new Point(r.x + r.width, r.y);
		Point pc = new Point(r.x, r.y + r.height);
		Point pd = new Point(r.x + r.width, r.y + r.height);

		int speedx1 = Obj1.getx_speed() - Obj1.getx_nspeed();
		int speedy1 = Obj1.gety_speed() - Obj1.gety_nspeed();
		int speedx2 = Obj2.getx_speed() - Obj2.getx_nspeed();
		int speedy2 = Obj2.gety_speed() - Obj2.gety_nspeed();

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

	/*
	 * public boolean col(Entity Obj1, Entity Obj2, int cote) { Rectangle R =
	 * Obj1.getHitbox().getRect(); Point p1 = new Point(R.x, R.y); Point p2 = new
	 * Point(R.x + R.width, R.y); Point p3 = new Point(R.x, R.y + R.height); Point
	 * p4 = new Point(R.x + R.width, R.y + R.height);
	 * 
	 * Rectangle r = Obj2.getHitbox().getRect(); Point pa = new Point(r.x, r.y);
	 * Point pb = new Point(r.x + r.width, r.y); Point pc = new Point(r.x, r.y +
	 * r.height); Point pd = new Point(r.x + r.width, r.y + r.height);
	 * 
	 * int speedx1 = Obj1.getx_speed() - Obj1.getx_nspeed(); int speedy1 =
	 * Obj1.gety_speed() - Obj1.gety_nspeed();
	 * 
	 * int speedx2 = Obj2.getx_speed() - Obj2.getx_nspeed(); int speedy2 =
	 * Obj2.gety_speed() - Obj2.gety_nspeed();
	 * 
	 * switch (cote) { case 1: return (pa.getX() + speedy2 + speedx2 == p2.getX() +
	 * speedx1 + speedy1 && ((pa.getY() + speedy2 + speedx2 >= p2.getY() + speedy1 +
	 * speedx1 && pa.getY() + speedy2 + speedx2 <= p4.getY() + speedx1 + speedy1) ||
	 * (p4.getY() + speedy2 + speedx2 >= pb.getY() + speedy1 + speedx1 && p4.getY()
	 * + speedy1 + speedx1 <= pc.getY() + speedy2 + speedx2))) || (pa.getY() +
	 * speedy2 + speedx2 == p3.getY() + speedy1 + speedx1 && ((pa.getX() + speedy2 +
	 * speedx2 >= p3.getX() + speedx1 + speedy1 && pa.getX() + speedy2 + speedx2 <=
	 * p4.getX() + speedx1 + speedy1) || (p4.getX() + speedx1 + speedy1 <= pb.getX()
	 * + speedy2 + speedx2 && p4.getX() + speedx1 + speedy1 >= pa.getX() + speedy2 +
	 * speedx2))); case 2: return (pb.getX() + speedy2 + speedx2 == p1.getX() +
	 * speedx1 + speedy1 && ((pb.getY() + speedy2 + speedx2 >= p1.getY() + speedy1 +
	 * speedx1 && pb.getY() + speedy2 + speedx2 <= p3.getY() + speedy1 + speedx1) ||
	 * (p3.getY() + speedy1 + speedx1 >= pa.getY() + speedy2 + speedx2 && p3.getY()
	 * + speedy1 + speedx1 <= pc.getY() + speedy2 + speedx2))) || (pb.getY() +
	 * speedy2 + speedx2 == p3.getY() + speedy1 + speedy1 && ((pb.getX() + speedy2 +
	 * speedx2 >= p3.getX() + speedx1 + speedy1 && pb.getX() + speedy2 + speedx2 <=
	 * p4.getX() + speedx1 + speedy1) || (p3.getX() + speedx1 + speedy1 <= pb.getX()
	 * + speedy2 + speedx2 && p3.getX() + speedx1 + speedy1 >= pa.getX() + speedy2 +
	 * speedx2))); case 3: return (pc.getX() + speedy2 + speedx2 == p1.getX() +
	 * speedx1 + speedy1 && ((pc.getY() + speedy2 + speedx2 >= p2.getY() + speedy1 +
	 * speedx1 && pc.getY() + speedy2 + speedx2 <= p4.getY() + speedy1 + speedx1) ||
	 * (p2.getY() + speedy1 + speedx1 >= pb.getY() + speedy2 + speedx2 && p2.getY()
	 * + speedx1 + speedy1 <= pd.getY() + speedy2 + speedx2))) || (pc.getY() +
	 * speedy2 + speedx2 == p2.getY() + speedy1 + speedx1 && ((pc.getX() + speedy2 +
	 * speedx2 <= p2.getX() + speedx1 + speedy1 && pc.getX() + speedy2 + speedx2 >=
	 * p1.getX() + speedx1 + speedy1) || (p2.getX() + speedy1 + speedx1 >= pb.getX()
	 * + speedy2 + speedx2 && p2.getX() + speedx1 + speedy1 <= pd.getX() + speedy2 +
	 * speedx2))); case 4: return (pd.getX() + speedy2 + speedx2 == p2.getX() +
	 * speedx1 + speedy1 && ((pd.getY() + speedy2 + speedx2 >= p1.getY() + speedy1 +
	 * speedx1 && pd.getY() + speedy2 + speedx2 <= p3.getY() + speedy1 + speedx1) ||
	 * (p1.getY() + speedy1 + speedx1 >= pb.getY() + speedy2 + speedx2 && p1.getY()
	 * + speedx1 + speedy1 <= pd.getY() + speedy2 + speedx2))) || (pd.getY() +
	 * speedy2 + speedx2 == p2.getY() + speedy1 + speedx1 && ((pd.getX() + speedy2 +
	 * speedx2 <= p2.getX() + speedx1 + speedy1 && pd.getX() + speedy2 + speedx2 >=
	 * p1.getX() + speedx1 + speedy1) || (p1.getX() + speedy1 + speedx1 >= pc.getX()
	 * + speedy2 + speedx2 && p1.getX() + speedx1 + speedy1 <= pd.getX() + speedy2 +
	 * speedx2))); } return true; }
	 * 
	 * public boolean collisionR(Entity Obj1, Entity Obj2) {
	 * 
	 * return (col(Obj1, Obj2, 1) || col(Obj1, Obj2, 2) || col(Obj1, Obj2, 3) ||
	 * col(Obj1, Obj2, 4)) || (col(Obj2, Obj1, 1) || col(Obj2, Obj1, 2) || col(Obj2,
	 * Obj1, 3) || col(Obj2, Obj1, 4));
	 * 
	 * return col(Obj1, Obj2); }
	 */

	public ArrayList<Entity> collision(Entity Obj, ArrayList<Entity> list) {
		ArrayList<Entity> col = new ArrayList<Entity>();
		boolean collision = false;

		for (int i = 0; i < list.size(); i++) {
			Entity elem = list.get(i); // on récupére un a un les elements de la liste
			collision = collisionR(Obj, elem); // calcul de la collision entre l'objet et l'élément de la liste.

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
			case 7:
				/*
				 * if(collision && ((Porte) Obj).type==0) { col.add(elem); }
				 */
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

	public boolean collision(Entity Obj1, Entity Obj2, int rayon) {
		int X1 = Obj1.getx() + Obj1.getx_speed() - Obj1.getx_nspeed();
		int Y1 = Obj1.gety() + Obj1.gety_speed() - Obj1.gety_nspeed();

		int X2 = Obj2.getx() + Obj2.getx_speed() - Obj2.getx_nspeed();
		int Y2 = Obj2.gety() + Obj2.gety_speed() - Obj2.gety_nspeed();

		int R = Obj2.getHitbox().getRayon();
		int r = rayon;
		if (rayon == 0)
			r = Obj1.getHitbox().getRayon();

		return distance(X1, Y1, X2, Y2) < r + R;
	}
}
