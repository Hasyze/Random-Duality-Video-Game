package automaton;

import java.util.LinkedList;

import info3.game.automata.ast.* ;
import info3.game.automata.util.* ;
import info3.game.automata.parser.* ;

public class Transition {
	Boolean condition;
	Etat target;
	Action actions;
	
	public Transition(Etat target) {
		this.condition = true;
		this.target = target;
		this.actions = new Action(new LinkedList<FunCall>());
	LinkedList<Parameter> p = new LinkedList<Parameter>();
	p.add(null);
	FunCall move = new FunCall(move, 37);
	}
	
	
	
	public void addAction(FunCall a) {
		actions.calls.add(a);
	}
}
