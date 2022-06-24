package automaton;

import Entities.Direction;
import Entities.Entity;

public class Move implements IAction{
		
	Direction dir;
	
	public Move() {
		dir = Direction.F;
	}
	
    public Move(String s) {
    	switch(s) {
    	case"N":
    		dir = Direction.N;
    		break;
    	case"S":
    		dir = Direction.S;
    		break;
    	case"E":
    		dir = Direction.E;
    		break;
    	case"W":
    		dir = Direction.W;
    		break;
    	
    	}
    }

	@Override
	public void apply(Entity e) {
		e.move(dir);
	}
	
}
