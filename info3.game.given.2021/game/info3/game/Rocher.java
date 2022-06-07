package info3.game;

import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import java.util.Random;

public class Rocher extends StaticEntity{
	
	int m_width;
	
	Rocher() throws IOException {
		super();
		this.m_images=loadSprite("resources/images_test/rocher.png", 2, 5);
		this.x = position_rocher_x();
		this.y = position_rocher_y();
		
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
	
	int position_rocher_x() {
		Random random= new Random();
		int nb;
		nb= random.nextInt(1024);
		return nb;
	}
	
	int position_rocher_y() {
		Random random= new Random();
		int nb;
		nb= random.nextInt(768);
		return nb;
	}
	
	
	
	static Rocher[] init_rocher() {
		Random random= new Random();
		int nb;
		nb= random.nextInt(9);
		System.out.print("Nb rocher ");
		System.out.print(nb);
		Rocher m_rocher[]=new Rocher[nb];
		for(int i=0;i<nb;i++) {
			try {
				m_rocher[i]=new Rocher();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m_rocher;
	}
	
	public void paint(Graphics g, int width, int height) {
		m_width = width;
		BufferedImage img = m_images[0];
		int scale = 2;
		g.drawImage(img, this.x, this.y, scale * img.getWidth(), scale * img.getHeight(), null);
	}
}
