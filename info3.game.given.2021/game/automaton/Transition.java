package automaton;

import Entities.*;

public class Transition {
	ICondition condition;
	Etat source;
	Etat target;
	IAction action;
	

	public Transition(ICondition condition, Etat source, Etat target, IAction action) {
		this.condition = condition;
		this.source = source;
		this.target = target;
		this.action = action;
	}

	public Etat testTransition(Entity e) throws Exception {
		if (condition.eval(e)) {
			action.apply(e);
			return target;
		}
		return null;
	}
}