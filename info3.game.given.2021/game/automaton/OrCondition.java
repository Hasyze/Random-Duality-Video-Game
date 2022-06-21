package automaton;

public class OrCondition implements IFunction{
	IFunction left;
	IFunction right;

	public OrCondition(IFunction l, IFunction r) {
		left = l;
		right = r;
	}

	@Override
	public boolean eval(IAction action) {
		return left.eval(action) || right.eval(action);
	}
}
