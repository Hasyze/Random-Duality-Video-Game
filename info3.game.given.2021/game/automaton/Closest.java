package automaton;

import Entities.Direction;
import Entities.Entity;

public class Closest implements ICondition{

	@Override
	public boolean eval(Entity e) throws Exception {
		return closest(e,e.direction);
	}

	//@Override
	public boolean closest(Entity e, Direction dir) {
		switch (dir) {
		case N:
			//return collision(e.getHitboxVoisin(N)
		case S:
		case W:
		case E:
		}
	}

}
