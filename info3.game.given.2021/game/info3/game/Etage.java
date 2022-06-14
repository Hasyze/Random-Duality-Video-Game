package info3.game;

import java.util.Random;

public class Etage {
	Salle[] salles;
	int Niveau;
	
	Etage(int niveau) {
		salles = null;
		Niveau = niveau;
	}
	
	public void test_etage() throws Exception {
		try {
			System.out.println("Test création map");
			this.creer_map(Niveau);
			//this.afficher_etage();
			} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}
	
	
	public void initialisation_etage() {	//Créer l'entrée
		Random r = new Random();
		int j = r.nextInt(3) +1;
		salles[0] = new Salle(j, "Entrée ");
	}
	
	public void creer_map(int niveau) {	//Initialise l'étage, créer les salles, avec leurs portes, fait le lien entre les portes, insèere la salle du boss
		
		salles = new Salle[niveau+3];
		
		initialisation_etage();
		
		for (int i = 1; i < niveau+2; i++ ) {	// Création des salles entre l'entrée et le boss
			int chiffre_valide = 0;
			int j = 0;
			int x = nbr_de_portes_dispo(i);
			while(chiffre_valide == 0) {	//On evite de créer un boucle qui empecherai le joueur d'atteindre la salle du boss
				Random r = new Random();	//Pour ça on fait un nombre de porte impaire
				j = r.nextInt(3) +1;
				if (x != j) {
					chiffre_valide = 1;
				}
			}
			if (i == niveau+1) {
				j = x+1;
			}
			salles[i] = new Salle(j, "Normale");
		}
		
		for (int i = 0; i < niveau+1; i++ ) {		//On lie les salles entres elles avant de faire celle du boss
			int j = 1;
			while (salles[i].Porte_non_liees() == true) {
				if (salles[i+j].Porte_non_liees() == true) {
					salles[i].Lier_deux_Salles(salles[i+j]);
				}
				j++;
				if (i+j >= niveau+2) {
					if ( (salles[i].Porte_non_liees() == true) && (salles[i+j-1].Porte_non_liees() == false) ) { //Si l'on se retrouve bloqué car pas assez de porte dans les salles suivantes
						salles[i].Retirer_Portes(salles[i].Trouver_porte_disponible());
					}
					j = 1;
				}
				
			}
		}
		
		salles[niveau +2] = new Salle("Boss   ");	//On créer la salle du boss et on la relie avec la salle précédente
		if (salles[niveau+1].Porte_non_liees() == false) {	//Si pas assez de porte dans la salle précédent le boss
			salles[niveau+1].Ajouter_portes(1);				//On en rajoute une
		}
		while (salles[niveau+1].Porte_non_liees() == true) { //On relies les portes de la dernière salle au boss.
			salles[niveau+2].Ajouter_portes(1);
			salles[niveau+1].Lier_deux_Salles(salles[niveau+2]);
		}
		
	}
	
	int nbr_de_portes_dispo(int indice) { //Renvoie le nombre de portes des i premières salles du tableau salles[]
		int sum_portes = 0;
		for (int i = 0; i < indice; i++) {
			sum_portes = Math.abs(salles[i].nbr_portes - sum_portes);
		}
		return sum_portes;
	}
	
	public void afficher_etage() {
		String porte_horiz_double = " <---> ";
		String porte_horiz_O = " <---- ";
		String porte_horiz_E = " ----> ";
		String porte_verti_double = "   I   ";
		String porte_verti_N = "   T   ";
		String porte_verti_S = "   L   ";
		
		/**
		for (int x = 0; x<salles.length; x++) {
			String ligne = new String();
			ligne = "Salle indice ";			
			for (int y = 0; y<1; y++) {
				for (int i = 0; i<6; i++) {
					if ((Salles[i].x == x) && (Salles[i].y == y)) {
						ligne = ligne.concat(Salles[i].Type);
					}
					else {
						ligne = ligne.concat("       ");
					}
				}
			}
			System.out.println(ligne);
		}**/
	}

}

