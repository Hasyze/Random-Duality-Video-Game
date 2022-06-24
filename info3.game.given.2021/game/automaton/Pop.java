package automaton;

import Entities.Entity;

public class Pop implements IAction {
	
	public Pop() {
		
	}
	@Override
	public void apply(Entity e) {
		e.stop(e.direction);
		
	}

}
