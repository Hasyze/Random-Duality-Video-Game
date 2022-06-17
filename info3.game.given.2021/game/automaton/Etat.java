package automaton;

import java.util.LinkedList;
import java.util.List;

public class Etat {
	String name;
	List<Transition> transitions;
	
	public Etat(String name) {
		this.name = name;
		transitions = new LinkedList<Transition>();
		
	}


	public void addTransition(Transition t){
		
		transitions.add(t);
	}
	
	
}
