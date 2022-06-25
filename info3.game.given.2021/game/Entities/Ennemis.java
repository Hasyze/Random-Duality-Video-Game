package Entities;

import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Game;

public class Ennemis extends Entity {

	public Ennemis(int m_x, int m_y, String name, int r, Game game) throws IOException {
		super(game, name);
		m_images = loadSprite("resources/modgenerator.png", 3, 8);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		type = 1;
		speed = 1;
	}

	public void tick(EntityManager EM, long elapsed) throws IOException {
		super.tick(EM, elapsed);
	}

	public void move(Direction dir) {
		super.move(dir);
		if (moveCD <= 0)
			moveCD = 25;
	}

}
