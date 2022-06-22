package automaton;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;

import Entities.*;

public class Transition {
	ICondition condition;
	Etat source;
	Etat target;
	Map<IAction, Integer> actions;
	
	int value = 0;
	IAction toApply = null;
	
	

	public Transition(ICondition condition, Etat source, Etat target) {
		this.condition = condition;
		this.source = source;
		this.target = target;
		this.actions = new HashMap<IAction, Integer>();
	}

	public boolean testTransition(Entity e) throws Exception {
		return condition.eval(e);
	}

	public void choose(IAction action, int percent) {
		if ((value - percent) <= 0 && value>0) {
			toApply = action;
		}
		this.value -= percent;
	}
	
	BiConsumer<IAction, Integer> apply = (action, percent) -> choose(action, percent);

	public Etat applyTransition(Entity e) throws Exception{
		if(actions.isEmpty())
			return null;
		Random r = new Random();
		this.value = r.nextInt(100);	
		actions.forEach(apply);
		toApply.apply(e);
		return target;
	}

	public void addIAction(IAction a, int percent) {
		actions.put(a, percent);
	}	
	
	public void test() throws Exception{
		Random r = new Random();
		this.value = r.nextInt(100);
		System.out.println("Valeur tirée :"+value);
		actions.forEach(apply);
		System.out.println("Reste :"+value+"Action : "+toApply);
	}
	
	public static void main(String args[]) throws Exception {
			Transition t = new Transition(new True(),new Etat("Init"), new Etat("Truc")); 
			t.addIAction(new Stop(), 90);
			t.addIAction(new Move(), 10);
			for(int i = 0; i<100; i++) {
				t.test();
			}		
	}
}