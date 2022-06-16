package info3.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Entity extends Object{ 
		int x;
		int y;
		Hitbox hitbox;
		int x_speed;
		int y_speed;
		int x_nspeed;
		int y_nspeed;
		BufferedImage[] m_images;
		int vie;
		
		int m_imageIndex;
		long m_imageElapsed;
		long m_moveElapsed;
		int m_width;
		
		public void pop() {}
		public void wizz() {}
		public Entity egg() {return this;}
		public void transfert(Entity e){}
		
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
		public void destruct() {};
		
				
}
