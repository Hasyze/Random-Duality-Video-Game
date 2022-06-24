package Entities;

import java.io.IOException;

import info3.game.Game;

public class Fantome extends Cowboy {

	public Fantome(int m_x, int m_y, String name, int r, Game game) throws IOException {
		super(m_x, m_y, name, r, game);
		m_images = loadSprite("resources/images_test/idle_resize.png", 4, 12);
		this.Name = name;
		type = 4;
		speed = 5;
	}

	public void pop() {
		
	}
	
}
