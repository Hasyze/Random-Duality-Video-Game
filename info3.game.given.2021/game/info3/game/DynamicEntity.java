package info3.game;

import java.awt.image.BufferedImage;

public abstract class DynamicEntity extends Entity{
	
	int vie;
	int x;
	int y;
	BufferedImage[] m_images;
	Hitbox hitbox;
	
	
	
	int x_speed;
	int y_speed;
	int x_nspeed;
	int y_nspeed;
	
	int m_imageIndex;
	long m_imageElapsed;
	long m_moveElapsed;
	int m_width;
	
	public DynamicEntity(){
		this.vie=1;
		hitbox= new Hitbox (25,x,y); 

	}
	
	int speed = 4;
	
	public void set_orientation() {
		// Version un peu moche, verifier le format des sprites, cherche une nouvelle
		// solution
		// Ajouter un chant orientation pour les projectiles ?
		if (x_speed > 0) {
			if (y_speed > 0) {
				m_imageIndex = 23;//GOOD
			} else if (y_nspeed > 0) {
				m_imageIndex = 16;//GOOD
			}else {
				m_imageIndex = 20; //GOOD
				}
		} else if (x_nspeed > 0) {
			if (y_speed > 0) {
				m_imageIndex = 4;//GOOD
			} else if (y_nspeed > 0) {
				m_imageIndex = 10;//GOOD
			}else {
				m_imageIndex = 7;//GOOD
			}
		}else if (y_speed > 0) {
			m_imageIndex = 1;//GOOD
			
		}else if (y_nspeed > 0) {
			m_imageIndex = 13;//GOOD
			
		}
	}
	
	 
	
	
	public void set_speed(int code, int speed) {
		System.out.println("déplacement");
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
