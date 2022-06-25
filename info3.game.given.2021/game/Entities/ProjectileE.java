package Entities;

import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Modele;

public class ProjectileE extends Entity {
	
	ProjectileE(Direction Dir, Modele Mod, EntityManager EM, int x, int y) throws IOException{
		super(Mod, EM);
		m_images = loadSprite("resources/sword.png", 1, 1);
		this.direction = Dir;		
		this.type = 2;
		this.x = x;
		this.y = y;
		int heigt_hb = (m_images[0].getHeight()) / 2;
		int width_hb = (m_images[0].getWidth()) / 2;
	
		hitbox = new Hitbox(5, x + width_hb, y + heigt_hb, 0);
	}
}
