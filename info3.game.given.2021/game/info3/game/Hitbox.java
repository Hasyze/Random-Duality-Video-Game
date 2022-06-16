package info3.game;

public class Hitbox {

	int rayon;
	int x, y;
	public Hitbox(int rayon, int m_x, int m_y) {
		this.rayon = rayon;
		this.x = m_x;
		this.y = m_y;
	}
	
	double distance (int a_x,int a_y, int b_x, int b_y) {
		return Math.sqrt((a_x-b_x)*(a_x-b_x)+(a_y-b_y)*(a_y-b_y));
	}


	boolean verif_collision(Entity a) {
		return distance (this.x,this.y,a.x+a.x_speed-a.x_nspeed,a.y-a.y_nspeed+a.y_speed)<this.rayon+a.hitbox.rayon;
	}
	boolean collision(DynamicEntity a) {
		return distance (this.x,this.y,a.x,a.y)<this.rayon+a.hitbox.rayon;
	}
	boolean collision(StaticEntity a) {
		return distance(x,y,a.x,a.y)<rayon+a.hitbox.rayon;
	}
}
