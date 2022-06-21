package automaton;

import Entities.Entity;

public class Stop implements IAction {
	
	public Stop() {
		
	}
	@Override
	public void apply(Entity e) {
		e.stop(e.direction);
		
	}

}
