package info3.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Entity extends Object{ 
		public int[] Aut;
		public int Vie;
		public void step(ArrayList<Entity> New_Dynamic,ArrayList<Entity> New_Satic) {};
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
