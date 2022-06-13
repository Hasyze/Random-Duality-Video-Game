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
		hitbox= new Hitbox (25,x,y); 
	}
	public StaticEntity (int s_x, int s_y) {
		x= s_x;
		y=s_y;
		hitbox = new Hitbox(35,x,y);
	}
	

}
