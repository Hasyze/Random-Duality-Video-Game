package info3.game;

import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;

public class Rocher extends StaticEntity{
	
	int m_width;
	
	
	
		
	Rocher(int m_x, int m_y) throws IOException{
		super(m_x,m_y);
		this.m_images=loadSprite("resources/images_test/rocher.png", 2, 5);
		x=m_x;
		y=m_y;
	}
	
	
	
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
	
	public void paint(Graphics g, int width, int height) {
		m_width = width;
		BufferedImage img = m_images[0];
		int scale = 2;
		g.drawImage(img, this.x, this.y, scale * img.getWidth(), scale * img.getHeight(), null);
	}
}
