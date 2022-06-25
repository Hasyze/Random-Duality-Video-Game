package Entities;

import java.io.IOException;
import java.util.Random;

import info3.game.EntityManager;
import info3.game.Game;

public class Boss extends Entity {

	public Boss(int m_x, int m_y, Game game) throws IOException {
		super(game,"Boss");
		m_images = loadSprite("resources/modgenerator.png", 3, 8);
		this.Name = "Boss";
		x = m_x;
		y = m_y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
		hitbox = new Hitbox(40, x + width_hb, y + heigt_hb, 0);
		type = 1;
		speed = 4;
		vie = 25;
		moveCDR = 20;

	}
	
	public void tick(EntityManager EM, long elapsed) throws IOException {
		super.tick(EM, elapsed);
	}

	
	public void egg(Direction dir) {
		System.out.println("PULLULE");
		Random r = new Random();
		int j = r.nextInt(3) + 2;
		for (int i = 0;  i < j; i++) {
			try {
				game.EM.EM_add(new Ennemis(x, y, "Ennemie1", 20, game));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		wait = waitR;
	}
}
