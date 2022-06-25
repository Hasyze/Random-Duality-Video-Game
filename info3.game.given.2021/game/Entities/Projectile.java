package Entities;

import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Game;
import info3.game.Modele;

public class Projectile extends Entity {
		
	long duree = 4000;
	int tickRate = 25;
	
	public Projectile(int m_x, int m_y, int type, Direction dir,int speed, int tickRate, int duree, String name, int r, Game game) throws IOException {
		super(game, name);
		m_images = loadSprite("resources/images_test/marron_20x20.jpg", 1,1);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		this.type = type;
		this.speed = speed;
		this.tickRate = tickRate;
		this.direction = dir;
		this.duree = duree;
		vie = 1;
	}
	
	
	long test = 250;
	public void tick(EntityManager EM, long elapsed) throws IOException {
		super.tick(EM, elapsed);
		duree -= elapsed;
		if(duree<0) {
			vie = 0;
		}
		test -= elapsed;
		if(test<0) {
			//System.out.println("Direction"+direction);
		}
	}

	public void move(Direction dir) {
		super.move(dir);
		if (moveCD <= 0)
			moveCD = tickRate;
	}
}
