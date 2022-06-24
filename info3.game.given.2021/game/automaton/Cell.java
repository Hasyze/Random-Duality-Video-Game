package automaton;

import Entities.Direction;
import Entities.Entity;

public class Cell implements ICondition{
	
	Direction dir;
	Type type;
	
	public Cell(Direction dir, Type type) {
		this.dir = dir;
		this.type = type;
	}
	
	@Override
	public boolean eval(Entity e) throws Exception {
		return e.cell(dir,type);
	}

}
