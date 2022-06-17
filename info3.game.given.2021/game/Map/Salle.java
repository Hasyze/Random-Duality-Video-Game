package Map;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Salle extends Etage{

	Image background;
	
	
	public Salle() {
		
	}
	
	public void init_background() throws IOException{
		Image[] background = new Image[2];
		background[0] = loadImage("resources/images_test/sprite_mur.png") ;
		background[1] = loadImage("resources/images_test/sprite_mur2.png") ;
		Random rand = new Random();
		int r = rand.nextInt(2);
		this.background = background[r];
	}
	
	//Fonction pour récupérer une image
	public static Image loadImage(String filename) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		}
		return null;
	}
	
}
