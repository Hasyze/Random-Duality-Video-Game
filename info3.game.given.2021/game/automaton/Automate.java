package automaton;

import java.util.Iterator;
import java.util.List;

import info3.game.automata.ast.* ;
import info3.game.automata.util.* ;
import info3.game.automata.parser.* ;

public class Automate {
	public String name;
	public Etat current_state;
	
	public Automate() {
		name = "test";
		current_state = new Etat("Init");	
	}
	
	public Etat current() {
		return current_state;
	}
	
	public void step() {
		Iterator<Transition> i = current_state.transitions.iterator();
		Transition t = null;
		while( i.hasNext()) {
			t = (Transition)i.next();
			if(t.condition) {
				current_state = t.target;
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		Automate a = new Automate();
		Etat current = a.current();
		Etat etat2 = new Etat("Etat 2");
		Transition t = new Transition(etat2,current);
		current.addTransition(t);
		System.out.println("L'etat initial se nomme" + a.current().name);
		a.step();
		System.out.println("L'etat après le step se nomme" + a.current().name);		
		
	}
}
