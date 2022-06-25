package automaton;

import Entities.Entity;

public class Egg implements IAction{

	public Egg() {
	}

	@Override
	public void apply(Entity e) {
		e.egg();
	}
}
