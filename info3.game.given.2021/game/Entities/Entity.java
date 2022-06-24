package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import automaton.Automate;
import automaton.Type;
import info3.game.EntityManager;
import info3.game.Game;
import info3.game.Modele;

public abstract class Entity extends Object {

	public Automate Aut;

	// a enlever :
	public String Name;
	//
	protected BufferedImage[] m_images;
	protected int m_imageIndex;
	protected Game game;

	protected int x = 10, y = 10;
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
	protected List<Entity> close;
	/**
	 * Type 0: Joueur 1: Ennemi 2: Missile Ennemi 3: Missile Joeur 4: Fnatome 5:
	 * Rocher 6: Mur 7: Porte
	 **/

	public Direction direction = Direction.N;

	// Stats
	protected int speed; //
	protected int vie; //
	
	// Cooldowns
	protected long moveCD = 0; 


	public Entity(Game game) {	
		this.Name = "Undefined";
		this.game = game;
		m_images = null;
		m_imageIndex = 0;
		this.type = -1; 
		this.modele = game.modele;
		this.EM = game.EM;		
		vie = 1000;
		speed = 0;
	}
	
	public Entity(Modele modele, String Name) {
		System.out.print(Name);
		this.Name = Name;
		
		this.modele = modele;
		close = new ArrayList<Entity>();
		for (int i = 0; i < 8; i++) {
			close.add(i, null);
		}
		m_images = null;
		m_imageIndex = 0;
		vie = 1;
		speed = 4;
	}

	public void move(Direction dir) {
		this.direction = dir;
		if(moveCD > 0) {
			return;
		}
		switch (dir) {
		case F:
			move(this.direction);
			break;
		case NW:
			x-=speed;
			y-=speed;
			break;
		case NE:
			x+=speed;
			y-=speed;
			break;
		case SW:
			x-=speed;
			y+=speed;
			break;
		case SE:
			x+=speed;
			y+=speed;
			break;
		case W:
			x-= speed;
			break;
		case E:
			x+=speed;
			break;
		case N:
			y-=speed;
			break;
		case S:
			y+=speed;
			break;
		default:
			break;
		}	
	}

	public void transfert(Entity e) {
		Automate temp = this.Aut;
		this.Aut = e.Aut;
		e.Aut = temp;
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
	

	public void setVie(int i) {
		vie += i;
	}

	public void step() throws Exception {
		this.Aut.step(this);
	}

	public void tick(EntityManager em,long elapsed) throws IOException {
		moveCD += elapsed;
		hitbox.relocate(x, y);
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

	public int getTypeInt() {
		return type;
	}
	public Type getType() {
		switch(type) {
		case 0:
			return Type.PLAYER;
		case 1:
			return Type.ADVERSAIRE;
		case 2:
			return Type.MISSILE;
		case 3:
			return Type.CLUE;
		case 4:
			return Type.VOID;
		case 5:
			return Type.JUMPABLE;
		case 6:
			return Type.OBSTACLE;
		case 7:
			return Type.GATE;		
		}
		return null;
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
	
	public boolean MyDir(Direction Dir) {
		return Dir==this.direction;
		}
	public boolean GotPower() {
		return vie>0;
	}
	public boolean GotStuff() {
		return false;
	}
		
	public boolean key(Key k) {
		return this.game.getListener().key(k);
	}
	public boolean cell(Direction dir, Type type) {
		return game.modele.collisions(this, game.EM.getStatic());
	}
}
