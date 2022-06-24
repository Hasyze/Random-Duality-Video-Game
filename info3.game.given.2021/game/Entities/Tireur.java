package Entities;

import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Game;

public class Tireur extends Entity{

	public Tireur(int m_x, int m_y, String name, int r, Game game) throws IOException {
		super(game);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
		this.Name = name;
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(r, x + width_hb, y + heigt_hb, 0);
		type = 0;
		speed = 4;
		vie = 10;		
	}
	
	public void tick(EntityManager EM , long elapsed) throws IOException {
		super.tick(EM, elapsed);
		moveCD += elapsed;
		if (moveCD > 24)
			moveCD = 0;
	}

}
