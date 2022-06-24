package Entities;

import java.io.IOException;

import info3.game.EntityManager;
import info3.game.Modele;

public class Ennemis extends Entity {

	private long m_imageElapsed;
	private long m_moveElapsed;

	public Ennemis(Modele modele, EntityManager EM) throws IOException {
		super(modele, EM);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}
	
	
	
	
	public void tick(long elapsed, EntityManager EM) {
		super.tick(EM, elapsed);
		m_imageElapsed += elapsed;
		if (m_imageElapsed > 1500) {
			m_imageElapsed = 0;

		}
		set_orientation();
	}
	
	public void set_orientation() {
		// Version un peu moche, verifier le format des sprites, cherche une nouvelle
		// solution
		// Ajouter un chant orientation pour les projectiles ?img.getWidth()
		if (x_speed > 0) {
			if (y_speed > 0) {
				m_imageIndex = 23;// GOOD
			} else if (y_nspeed > 0) {
				m_imageIndex = 16;// GOOD
			} else {
				m_imageIndex = 20; // GOOD
			}
		} else if (x_nspeed > 0) {
			if (y_speed > 0) {
				m_imageIndex = 4;// GOOD
			} else if (y_nspeed > 0) {
				m_imageIndex = 10;// GOOD
			} else {
				m_imageIndex = 7;// GOOD
			}
		} else if (y_speed > 0) {
			m_imageIndex = 1;// GOOD

		} else if (y_nspeed > 0) {
			m_imageIndex = 13;// GOOD

		}
	}
	public void hit() throws IOException {
		ProjectileE balle = new ProjectileE(direction, modele,EM,x,y);
		this.EM.EM_add(balle);
		}

	public void move(int code) {
		switch (code) {
		case 37:
		case 81:
			x_nspeed = speed;
			break;
		case 39:
		case 68:
			x_speed = speed;
			break;
		case 38:
		case 90:
			y_nspeed = speed;
			break;
		case 40:
		case 83:
			y_speed = speed;
			break;
		}
		// System.out.println("OUIIIIIIII");
		// .out.println(this.x + this.y);

	}
	public void stop(int code) {
		switch (code) {
		case 37:
		case 81:
			x_nspeed = 0;
			break;
		case 39:
		case 68:
			x_speed = 0;
			break;
		case 38:
		case 90:
			y_nspeed = 0;
			break;
		case 40:
		case 83:
			y_speed = 0;
			break;
		}
	}
}
