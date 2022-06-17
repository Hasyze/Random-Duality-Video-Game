package automaton;

import java.util.LinkedList;
import java.util.List;

import info3.game.automata.ast.* ;
import info3.game.automata.util.* ;
import info3.game.automata.parser.* ;


public class BotBuilder {
	
	
	List<Automaton> loadAutomata(String filename) {
	    try {
	      AST ast = (AST) AutomataParser.from_file(filename);
	      
	      // TODO à vous de constuire les automates à partir de l'AST
	      
	      List<Automaton> automata = new LinkedList<Automaton> ();
	      
	      
	      
	      String aut_name = new String();
	      State aut_initial = new State("truc");
		      
		      State source = new State("truc");
		      				
					    Condition condition = new Condition(EXPRESSION);
					    	List<FunCall> calls = new LinkedList<FunCall> ();
					  	Action action = new Action(calls);
					  	State target = new State("truc");
					  	
			      List<Transition> transitions = new LinkedList<Transition> ();
			      			      
		      Behaviour behaviour = new Behaviour(transitions);
		      Mode mode = new Mode(source, behaviour);
		      
		      List<Mode> aut_modes = new LinkedList<Mode> ();
		      
	      Automaton aut = new Automaton(aut_name, aut_initial, aut_modes);
	      
	      
	      return automata ;
	    } catch (Exception ex) {
	      return null;
	    }
	  }


}
