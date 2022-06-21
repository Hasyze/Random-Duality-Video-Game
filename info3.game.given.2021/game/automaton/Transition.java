package automaton;

public class Transition {
	IFunction condition;
	Etat source;
	Etat target;
	IFunction action;

	public Transition(IFunction condition, Etat source, Etat target, IFunction action) {
		this.condition = condition;
		this.source = source;
		this.target = target;
		this.action = action;
	}

	public Etat transition(IAction act) {
		if (condition.eval(act)) {
			return target;
		}
		return null;
	}
}