package automaton;

import Entities.Direction;
import Entities.Entity;

public class Move implements IAction{
		
	public Move() {
	}

	@Override
	public void apply(Entity e) {
		e.move(e.direction);
	}
	
}
