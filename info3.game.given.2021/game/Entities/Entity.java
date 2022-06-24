package Entities;

import java.awt.Graphics;
import automaton.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import automaton.Automate;
import info3.game.*;

public abstract class Entity extends Object{
	
	public Automate Aut; //
	public String Name; //
	protected Game game;
	protected BufferedImage[] m_images; //
	protected int m_imageIndex;
	protected int x = 0, y = 0;
	protected Hitbox hitbox; //
	protected int type; //
	protected Modele modele;
	protected EntityManager EM;
	public Direction direction = Direction.E; 
	
	// Stats
	protected int speed; //
	protected int vie; //
	
	// Cooldowns
	protected long moveCD = 0;
	protected long damageCD = 0;


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
		g.drawOval(x - originex - hitbox.getRayon(), y - originey - hitbox.getRayon(), hitbox.getRayon() * 2,
				hitbox.getRayon() * 2);
	}

	

	

	public void degatVie(int degat) {
		if(damageCD>0)
			return;
		vie -= degat;
		if(damageCD<=0)
			damageCD = 2000;
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
	
	public int getSpeed() {
		return speed;
	}
	

	public void setVie(int i) {
		vie += i;
	}

	public void step() throws Exception {
		this.Aut.step(this);
	}

	public void tick(EntityManager em,long elapsed) throws IOException {
		System.out.println(moveCD);
		if(moveCD>0)
			moveCD -= elapsed;
		if(damageCD>0)
			damageCD -= elapsed;
		hitbox.relocate(x, y);
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public int getType() {
		return type;
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
		return game.modele.collisions(this, game.EM.getStatic(),dir,type);
	}
}
