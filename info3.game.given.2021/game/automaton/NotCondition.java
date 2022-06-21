package automaton;

public class NotCondition implements IFunction{
	IFunction condition;
	
	public NotCondition(IFunction condition) {
		this.condition = condition;
	}

	@Override
	public boolean eval(IAction action) {
		return !(condition.eval(action));
	}
	
	

}
