/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Dr. Michaël Périn, Univ. Grenobles-Alpes
 */

package automaton;

import info3.game.automata.ast.*;
import info3.game.automata.ast.Transition;
import info3.game.automata.parser.AutomataParser;
import info3.game.automata.parser.AutomataParserConstants;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BotBuilder implements IVisitor {

	List<Automate> automates;
	Automate current;

	public BotBuilder() {
		automates = new LinkedList<Automate>();
	}

	@Override
	public Object visit(Category cat) {
		return cat.terminal.content;
	}

	@Override
	public Object visit(Direction dir) {
		return dir.terminal.content;
	}

	@Override
	public Object visit(Key key) {
		return key.terminal.content;
	}

	@Override
	public Object visit(Value v) {
		return v.value;
	}

	@Override
	public Object visit(Underscore u) {
		return u.toString();
	}

	@Override
	public void enter(FunCall funcall) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		if (parameters.isEmpty())
			return new AppelFonc(funcall.name, funcall.percent);
		else {
			AppelFonc af = new AppelFonc(funcall.name, funcall.percent);
			for (Object temp : parameters) {
				af.addArgument((String) temp);
			}
			return af;
		}
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		return new BinaryOperation((ICondition)convertAppelFonc((AppelFonc) left), (ICondition)convertAppelFonc((AppelFonc) right),
				operator.toString());
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		return new UnaryOperation((ICondition)convertAppelFonc((AppelFonc) expression), operator.toString());
	}

	@Override
	public Object visit(State state) {
		for(Etat temp : current.etats) {
			if(temp.name.equals(((State) state).toString())) {
				return temp;
			}
		}
		Etat e = new Etat(((State) state).toString());
		current.addEtat(e);
		return e;
	}

	@Override
	public void enter(Mode mode) {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		/*
		List<ATransition> transitions = (List<ATransition>) behaviour;
		for (Object transition : transitions) {
			((Etat) source_state).addTransition((ATransition) transition);
		}
		*/
		/*Etat e = null;
		for(Etat temp : current.etats) {
			if(temp.name == ((Etat)source_state).name) {
				e = temp;
			}
		}*/
		for(Object transitions : (List<ATransition>)behaviour) {
			((Etat)source_state).addTransition((ATransition) transitions);
		}
			
			
		
		return behaviour;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		
		return transitions;
	}

	@Override
	public void enter(Condition condition) {
	}

	@Override
	public Object exit(Condition condition, Object expression) {
		return convertAppelFonc((AppelFonc) expression);
	}

	@Override
	public void enter(Action action) {
	}

	@Override
	public Object exit(Action action, List<Object> funcalls) {
		Map<IAction, Integer> actions = new HashMap<IAction, Integer>();
		for (Object currentAction : funcalls) {
			actions.put((IAction) convertAppelFonc((AppelFonc) currentAction), ((AppelFonc)currentAction).percent);
		}
		return actions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		return new ATransition((ICondition)condition, (Etat)target_state, (Map<IAction, Integer>)action);
		// return new ATransition(convertCondition((Condition) condition), new
		// Etat(((State) target_state).toString()));
	}

	@Override
	public void enter(Automaton automaton) {
		current = new Automate(automaton.name, null, new LinkedList<Etat>(), null);
	}

	@Override
	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		current.current = current.etats.get(0);
		
		
		//current.addEtat((Etat)initial_state);
		/*current.current.addTransition(     ((List<ATransition>)(modes.get(0))).get(0)    );
		for (int i = 1; i<modes.size(); i++) {
			current.etats.get(i).addTransition(((List<ATransition>)(modes.get(i))).get(i));
		}*/
		
		return current;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visit(AST bot, List<Object> automata) {
		/*automata = bot.accept(this);
		for (Object temp : automata) {
			automates.add((Automate) temp);
		}*/
		return automata;
	}

	/*public ICondition convertCondition(Condition c) {
		switch (c.toString()) {
		case "True":
			return new True();
		default:
			System.out.println("Non ajouté dans le switch convertCondition dans BotBuilder :" + c.toString());
			return null;
		}
	}

	public IAction convertAction(FunCall f) {
		switch (f.toString()) {
		case "Move":
			return new Move();
		default:
			System.out.println("Non ajouté dans le switch convertAction dans BotBuilder :" + f.toString());
			return null;
		}
	}

	public IAction convertAction2(FunCall f, List<Object> arguments) {
		switch (f.toString()) {
		case "Move":
			return new Move();
		default:
			System.out.println("Non ajouté dans le switch convertAction2 dans BotBuilder :" + f.toString());
			return null;
		}
	}*/

	public Object convertAppelFonc(AppelFonc af) {
		switch (af.name) {
		case "Move":
			return new Move();
		case "Pop":
			return new Pop();
		case "True":
			return new True();
		default:
			System.out.println("Non ajouté dans le switch convertAction2 dans BotBuilder :" + af.name);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Automate> loadAutomata(String filename) {
		try {
			AST ast = (AST) AutomataParser.from_file(filename);

			List<Object> a = new LinkedList<Object>();
			a = (List<Object>) ast.accept(this);
			List<Automate> liste = new LinkedList<Automate>();

			for (Object current : a) {
				liste.add((Automate) current);
			}
			return liste;
		} catch (Exception ex) {
			return null;
		}
	}

}
