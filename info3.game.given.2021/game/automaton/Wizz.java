package automaton;

import Entities.Entity;

public class Wizz implements IAction{
	
	pubic Wizz() {
		
	}

	@Override
	public void apply(Entity e) {
		e.wizz();
		
	}

}
