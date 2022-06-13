package info3.game;

import java.awt.image.BufferedImage;

public class StaticEntity extends Entity{
	
	int x;
	int y;
	BufferedImage[] m_images;
	
	
	Hitbox hitbox;
	int m_imageIndex;
	
	public StaticEntity(){
		this.x=0;
		this.y=0;
	}
	

}
