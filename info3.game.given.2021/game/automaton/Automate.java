package automaton;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import info3.game.automata.ast.* ;
import info3.game.automata.util.* ;
import info3.game.automata.parser.* ;

public class Automate {
	public String name;
	public Etat current;
	public List<Etat> etats;
	
	public Automate() {
		name = "test";
		current = new Etat("Init");
		etats = new LinkedList<Etat>();
		etats.add(current);
	}
	
	public Automate(String name, Etat initial, List<Etat> etats) {
		this.name = name;
		this.current = initial;
		this.etats = etats;
	}
	
	public Etat current() {
		return current;
	}
	
	public void addEtat(Etat e) {
		etats.add(e);
	}
	
	public void addTransition(Transition t) {
		Etat e = null;
		for(int i = 0; i<etats.size(); i++) {
			e = etats.get(i);
			if(e.name == t.getSource().name) {
				e.addTransition(t);
				return;
			}
		}
	}
	
	public void step() {
		Etat e = current.transitions(null);
				
	}
	
	public boolean autStatic() {
		return (etats.size() == 1);
	}
	
	public static void main(String args[]) throws Exception {
		Automate a = new Automate();
		Etat current = a.current();
		Etat etat2 = new Etat("Etat 2");
		Transition t = new Transition(current,etat2);
		
		a.addEtat(etat2);
		a.addTransition(t);
		System.out.println("L'etat initial se nomme" + a.current().name);
		a.step();
		System.out.println("L'etat après le step se nomme" + a.current().name);		
		
	}
}
