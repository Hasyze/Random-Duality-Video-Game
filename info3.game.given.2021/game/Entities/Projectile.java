package Entities;

import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Game;
import info3.game.Modele;

public class Projectile extends Entity {
//type3
	public Projectile(int m_x, int m_y, int type,Direction dir, String name, int r, Game game) throws IOException {
		super(game);
		m_images = loadSprite("resources/image_test/marron_20x20.jpg", 1,1);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		this.type = type;
		speed = 1;
	}

	public void tick(EntityManager EM, long elapsed) throws IOException {
		super.tick(EM, elapsed);
	}

	public void move(Direction dir) {
		super.move(dir);
		if (moveCD <= 0)
			moveCD = 5;
	}
}
