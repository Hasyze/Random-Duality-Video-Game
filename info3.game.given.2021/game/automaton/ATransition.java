package automaton;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;


import Entities.*;

public class ATransition {
	ICondition condition;
	Etat target;
	Map<IAction, Integer> actions;
	
	int value = 0;
	List<IAction> toApply;
	
	
	public ATransition(ICondition condition, Etat target) {
		this.condition = condition;
		this.target = target;
		this.actions = new HashMap<IAction, Integer>();
		this.toApply = new LinkedList<IAction>();
	}
	
	public ATransition(ICondition condition, Etat target, Map<IAction, Integer> actions) {
		this.condition = condition;
		this.target = target;
		this.actions = actions;
		this.toApply = new LinkedList<IAction>();
	}

	public boolean testTransition(Entity e) throws Exception {
		System.out.println("Test tranzi :" + condition.eval(e));
		return condition.eval(e);
	}
	
	public void addIAction(IAction a, int percent) {
		actions.put(a, percent);
	}

	
	/* Fonction à donner au BiConsumer qui permet de choisir une action en fonction de son pourcentage.
	 * Pour se faire on génère un entier aléatoire entre 0 et 100 compris, en tant que variable global de la class (value)
	 * On effectue les tests suivant pour chaque actions:
	 * (1) - Si Value - %Action <= 0 --> Alors c'est cette action que l'on doit appliquer
	 * ET
	 * (2) - Si Value > 0 On a pas encore trouvé l'action à appliquer
	 * 
	 * Une fois l'action trouvée on la place dans toApply.
	 * Grâce au deuxième test une fois qu'une action est placée dans toApply on ne passe plus jamais dans la condition du if (2).
	 * 
	 * Comme la somme des % des actions est de 100 et la valeur max de value est 100, on trouve systématiquement une action
	 * à appliquer.
	 * 
	 * */
	
	public void choose(IAction action, int percent) {
		if(percent == -1) {
			toApply.add(action);
			return;
		}	
		if ((value - percent) <= 0 && value>0) {
			toApply.add(action);
			this.value -= percent;
		}
	}
	
	BiConsumer<IAction, Integer> apply = (action, percent) -> choose(action, percent);

	public Etat applyTransition(Entity e) throws Exception{
		if(actions.isEmpty())
			return null;
		Random r = new Random();
		this.value = r.nextInt(100);	
		actions.forEach(apply); //C'est ici qu'on applique nos tests a toutes les actions de la HashMap
		System.out.println("To Apply :"+ toApply);
		for(IAction a : toApply) {
			a.apply(e);
		}
		return target;
	}

	//Partie de test de cette class
	
	public void test() throws Exception{
		Random r = new Random();
		this.value = r.nextInt(100);
		System.out.println("Valeur tirée :"+value);
		actions.forEach(apply);
		System.out.println("Reste :"+value+"Action : "+toApply);
	}
	
	public static void main(String args[]) throws Exception {
			ATransition t = new ATransition(new True(), new Etat("Truc")); 
			t.addIAction(new Pop(), 90);
			t.addIAction(new Move(), 10);
			for(int i = 0; i<100; i++) {
				t.test();
			}		
	}
}