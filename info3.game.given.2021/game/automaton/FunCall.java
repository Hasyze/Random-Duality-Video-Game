package automaton;

import java.util.List;

import Entities.Direction;

public class FunCall implements IFunction {
	private enum Function {
		MOVE, STOP, POP, WIZZ, TRANSFERT, EGG, TRUE, MOVEOK
	}

	private Function name;
	List<String> arguments;

	public FunCall(String s, List<String> arguments) {
		switch (s) {
		//ACTIONS
		case "Pop":
			name = Function.POP;
			break;
		case "Wizz":
			name = Function.WIZZ;
			break;
		case "Move":
			name = Function.MOVE;
			break;
		case "Stop":
			name = Function.STOP;
			break;
		case "Transfert":
			name = Function.TRANSFERT;
			break;
		case "Egg":
			name = Function.EGG;
			break;
		//CONDITIONS
		case "True":
			name = Function.TRUE;
			break;
		case "Moveok":
			name = Function.MOVEOK;
			break;

		}
		this.arguments = arguments;
	}

	@Override
	public boolean eval(IAction action) {
		
		//VERIFIER SI LES ACTIONS DOIVENT RENVOYER UN BOOL
		
		switch (name) {
		
		//ACTIONS
		
		case MOVE:
			if (arguments.size() == 0) {
				action.move(Direction.N);
				break;
			} else {
				action.move(Direction.valueOf(arguments.get(0).toUpperCase()));
				break;
			}
		case STOP:
			if (arguments.size() == 0) {
				action.stop(Direction.N);
				break;
			} else {
				action.stop(Direction.valueOf(arguments.get(0).toUpperCase()));
				break;
			}
		case POP:
			action.pop();
			break;

		case WIZZ:
			action.wizz();
			break;

		case EGG:
			action.egg();
			break;

		case TRANSFERT:
			action.transfert();
			break;

		// CONDITIONS

		case TRUE:
			return true;

		case MOVEOK:
			return action.moveok();

		}
		return true;
	}

}
