package info3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class DynamicEntity extends Entity{
	
	int vie;
	int x;
	int y;
	BufferedImage[] m_images;
	
	
	int x_speed;
	int y_speed;
	int x_nspeed;
	int y_nspeed;
	
	int m_imageIndex;
	
	public DynamicEntity(){
		this.vie=0;
		this.x=0;
		this.y=0;
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
	

	/*public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
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
	}*/
	


	
	

}
