package info3.game;

public class Hitbox {
	/*
	 * int x_a; int y_a;
	 * 
	 * int x_b; int y_b;
	 * 
	 * int x_c; int y_c;
	 * 
	 * int x_d; int y_d;
	 */

	/*
	 * public Hitbox(int m_x, int m_y, int width, int height) { this.x_a = m_x;
	 * this.y_a = m_y; this.x_b = m_x + width; this.y_b = m_y; this.x_c = m_x +
	 * width; this.y_c = m_y + height; this.x_d = m_x; this.y_d = m_y + height; }
	 */
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
<<<<<<< HEAD
	
	boolean collision(StaticEntity a) {
		return distance (this.x,this.y,a.x,a.y)<this.rayon+a.hitbox.rayon;
	}
	boolean collision(DynamicEntity a) {
		return distance (this.x,this.y,a.x,a.y)<this.rayon+a.hitbox.rayon;

=======
	boolean verif(DynamicEntity a) {
		return distance (this.x,this.y,a.x+a.x_speed-a.x_nspeed,a.y-a.y_nspeed+a.y_speed)<this.rayon+a.hitbox.rayon;
	}
	boolean collision(DynamicEntity a) {
		return distance (this.x,this.y,a.x,a.y)<this.rayon+a.hitbox.rayon;
>>>>>>> 07c5e98063bed3099692086d09c7244035ce48eb
	}
}
