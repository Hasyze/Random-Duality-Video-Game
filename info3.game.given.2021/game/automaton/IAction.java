package automaton;

import Entities.*;

public interface IAction {
	
	public void move(Direction direction);
	public void stop(Direction direction);
	public void pop();
	public void wizz();
	public Entity egg();
	public void transfert();
	
	public boolean moveok();
}
