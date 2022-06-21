package automaton;

import java.util.LinkedList;
import java.util.List;

import Entities.Entity;


public class Automate {
	public String name;
	public Etat current;
	public List<Etat> etats;
	public Type type;
	
	public Automate() {
		name = "test";
		current = new Etat("Init");
		etats = new LinkedList<Etat>();
		etats.add(current);
	}
	
	public Automate(String name, Etat initial, List<Etat> etats, Type type) {
		this.name = name;
		this.current = initial;
		this.etats = etats;
		this.type = type;
	}
	
	public Etat current() {
		return current;
	}
	
	public void addEtat(Etat e) {
		etats.add(e);
	}
	
	public void addTransition(Transition t) {
		
	}
	
	public void step(Entity e) throws Exception {
		Etat etat = null;
		System.out.println("Etat passe de " + current.name);

		etat = current.testTransitions(e);
		if(etat != null) {
			current = etat;
		}
		System.out.println("a " + current.name);

				
	}
	
	public boolean autStatic() {
		return (etats.size() == 1);
	}
	
	public static void main(String args[]) throws Exception {
		
		
	}
}
