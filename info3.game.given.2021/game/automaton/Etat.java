package automaton;

import java.util.LinkedList;
import java.util.List;

import Entities.Entity;

public class Etat {
	String name;
	List<Transition> transitions;
	
	
	public Etat(String name) {
		this.name = name;
		transitions = new LinkedList<Transition>();	
	}
	public Etat(String name, List<Transition> list) {
		this.name = name;
		this.transitions = list;
	}
	public void addTransition(Transition t){		
		transitions.add(t);
	}
	
	public Etat doTransition(Entity e) throws Exception {
		for(int i = 0; i<transitions.size();i++) {
			if(transitions.get(i).testTransition(e)) {
				return transitions.get(i).applyTransition(e);
			}
		}
		return null;
	}	
}
