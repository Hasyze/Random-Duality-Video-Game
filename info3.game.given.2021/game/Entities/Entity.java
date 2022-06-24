package Entities;

import java.awt.Color;
import java.awt.Graphics;

import automaton.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import info3.game.*;

public abstract class Entity extends Object {

	public Automate Aut;

	// a enlever :
	public String Name;
	//
	protected BufferedImage[] m_images;
	protected int m_imageIndex;

	protected int x = 10, y = 10;
	protected int x_speed;
	protected int y_speed;
	protected int x_nspeed;
	protected int y_nspeed;
	protected Hitbox hitbox;
	protected Hitbox hitboxvoisinE;
	protected Hitbox hitboxvoisinW;
	protected Hitbox hitboxvoisinS;
	protected Hitbox hitboxvoisinN;
	protected Hitbox hitboxvoisinNE;
	protected Hitbox hitboxvoisinNW;
	protected Hitbox hitboxvoisinSE;
	protected Hitbox hitboxvoisinSW;
	protected int type;
	protected EntityManager EM;
	protected Modele modele;
	protected ArrayList<Entity> close;
	/**
	 * Type 0: Joueur 1: Ennemi 2: Missile Ennemi 3: Missile Joeur 4: Fnatome 5:
	 * Rocher 6: Mur 7: Porte
	 **/

	public Direction direction = Direction.N;

	// Stats
	protected int speed;
	protected int vie;

	public Entity(EntityManager em, Modele modele) {
		this.EM = em;
		EM.EM_add(this); // A MODIFIER LIST JAVA

		this.modele = modele;
		close = new ArrayList();
		for (int i = 0; i < 8; i++) {
			close.add(i, null);
		}
		m_images = null;
		m_imageIndex = 0;
		x_speed = 0;
		y_speed = 0;
		x_nspeed = 0;
		y_nspeed = 0;

		vie = 10;
		speed = 4;

	}

	public void move() {
	}

	public void move(Direction dir) {
		switch (dir) {

		case W:
			x_nspeed = speed;
			break;

		case E:
			x_speed = speed;
			break;

		case N:
			y_nspeed = speed;
			break;

		case S:
			y_speed = speed;
			break;
		default:
			break;
		}

	}

	public void stop(Direction dir) {
		switch (dir) {

		case W:
			x_nspeed = 0;
			break;

		case E:
			System.out.println("JE PASSE BIEN PAR ICI");
			x_speed = 0;
			break;

		case N:
			y_nspeed = 0;
			break;

		case S:
			y_speed = 0;
			break;
		default:
			break;
		}

	}

	public void transfert(Entity e) {
	}

	public Entity egg() {
		return null;
	}

	public void pop() {

	}

	public void wizz() {

	}

	public void paint(Graphics g, int originex, int originey) {
		BufferedImage img = m_images[m_imageIndex];
		int scale = 2;
		g.drawImage(img, x - originex - getWidth(), y - originey - getHeight(), scale * img.getWidth(),
				scale * img.getHeight(), null);

		// g.drawOval(x-originex-hitbox.getRayon(), y-originey-hitbox.getRayon(),
		// hitbox.getRayon()*2, hitbox.getRayon()*2);
		g.drawRect(x,y- hitbox.getRect().height/2,1024 + x,hitbox.getRect().height);
		g.drawRect(x-hitbox.getRect().width/2,y+hitbox.getRect().height/2,hitbox.getRect().width, 1024 + y);
		g.drawRect(0-hitbox.getRect().width/2,y-hitbox.getRect().height/2, x,hitbox.getRect().height);
		g.drawRect(x-hitbox.getRect().width/2,0, hitbox.getRect().width, y-hitbox.getRect().height/2);

	}

	public void add_close(Entity rocher) {
		switch (direction) {
		case N:
			System.out.println("Nord");
			if (info3.game.Modele.plus_proche(hitboxvoisinN, rocher)) {
				if (close.get(0) == null) {
					close.add(0, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(0).x, close.get(0).y)) {
						close.remove(0);
						close.add(0, rocher);
					}
				}

			}
			break;
		case S:
			System.out.println("Sud");
			if (info3.game.Modele.plus_proche(hitboxvoisinS, rocher)) {
				if (close.get(1) == null) {
					close.add(1, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(1).x, close.get(1).y)) {
						close.remove(1);
						close.add(1, rocher);
					}
				}

			}
			break;
		case E:
			System.out.println("E");
			if (info3.game.Modele.plus_proche(hitboxvoisinE, rocher)) {
				if (close.get(2) == null) {
					close.add(2, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(2).x, close.get(2).y)) {
						close.remove(2);
						close.add(2, rocher);
					}
				}

			}
			break;
		case W:
			System.out.println("West");
			if (info3.game.Modele.plus_proche(hitboxvoisinW, rocher)) {
				if (close.get(3) == null) {
					close.add(3, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(3).x, close.get(3).y)) {
						close.remove(3);
						close.add(3, rocher);
					}
				}

			}
			break;
		case NE:
			System.out.println("NordEst");
			if (info3.game.Modele.plus_proche(hitboxvoisinNE, rocher)) {
				if (close.get(4) == null) {
					close.add(4, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(0).x, close.get(0).y)) {
						close.remove(4);
						close.add(4, rocher);
					}
				}

			}
		case NW:
			System.out.println("NordWest");
			if (info3.game.Modele.plus_proche(hitboxvoisinNW, rocher)) {
				if (close.get(5) == null) {
					close.add(5, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(0).x, close.get(0).y)) {
						close.remove(5);
						close.add(5, rocher);
					}
				}

			}
		case SE:
			System.out.println("SudEst");
			if (info3.game.Modele.plus_proche(hitboxvoisinSE, rocher)) {
				if (close.get(6) == null) {
					close.add(6, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(0).x, close.get(0).y)) {
						close.remove(6);
						close.add(6, rocher);
					}
				}

			}
		case SW:
			System.out.println("SudWest");
			if (info3.game.Modele.plus_proche(hitboxvoisinSW, rocher)) {
				if (close.get(7) == null) {
					close.add(7, rocher);
				} else {
					if (info3.game.Modele.distance(this.x, this.y, rocher.x, rocher.y) < info3.game.Modele
							.distance(this.x, this.y, close.get(0).x, close.get(0).y)) {
						close.remove(7);
						close.add(7, rocher);
					}
				}

			}
		}
		
		System.out.println("N :");
		System.out.println(close.get(0));
		System.out.println("S :");
		System.out.println(close.get(1));
		System.out.println("E :");
		System.out.println(close.get(2));
		System.out.println("W :");
		System.out.println(close.get(3));

	}

	public void degatVie(int degat) {
		vie -= degat;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getvie() {
		return vie;
	}

	public EntityManager getEM() {
		return EM;
	}

	public void setVie(int i) {
		vie += i;
	}

	public void step(ArrayList<Entity> New_Dynamic, ArrayList<Entity> New_Static) {
		// TODO : step automates pour l'aut de chaque entity.
	}

	int m_moveElapsed = 0;

	public void tick(long elapsed) throws Exception {
		m_moveElapsed += elapsed;
		hitbox.relocate(x, y);
		if (m_moveElapsed > 24) {
			m_moveElapsed = 0;
			if (x_speed > 0 || y_speed > 0 || x_nspeed > 0 || y_nspeed > 0) {
				ArrayList<Entity> Dynamic = EM.getDynamic();
				ArrayList<Entity> col = modele.collision(this, Dynamic);
				if (col.isEmpty()) {

					x = (x + x_speed - x_nspeed);
					y = (y + y_speed - y_nspeed);

				} else {
					modele.interaction(this, col);
				}
			}
		}
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public Hitbox getHitboxVoisin(Direction dir) {
		switch (dir) {
		case N:
			return hitboxvoisinN;
		case S:
			return hitboxvoisinS;
		case E:
			return hitboxvoisinE;
		case W:
			return hitboxvoisinW;
		case NE:
			return hitboxvoisinNE;
		case NW:
			return hitboxvoisinNW;
		case SE:
			return hitboxvoisinSE;
		case SW:
			return hitboxvoisinSW;
		default:
			return null;
		}
	}

	public int getType() {
		return type;
	}

	public int get_speed() {
		return speed;
	}
	
	public int getx_speed() {
		return x_speed;
	}

	public int gety_speed() {
		return y_speed;
	}

	public int getx_nspeed() {
		return x_nspeed;
	}

	public int gety_nspeed() {
		return y_nspeed;
	}

	public int getWidth() {
		return m_images[m_imageIndex].getWidth();
	}

	public int getHeight() {
		return m_images[m_imageIndex].getHeight();
	}

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

}
