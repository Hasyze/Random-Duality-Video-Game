package info3.game;

import java.util.Random;

class Salle {	
	
	Porte nord;
	Porte sud;
	Porte est;
	Porte ouest;
	
	
	int nbr_portes;
	
	int[][] composition;
	/*Pour la composition, tableau en 2D
	 * -1 Mur
	 * -2 Porte
	 * -3 Rocher ?
	 * -4 Ennemis ?
	 * -
	 * -9 Zone "chemin" ? Faire differents layers
	 * */
	
	
	int largeur = 32;
	int hauteur = 18;
	int nb_ennemis = 0;
	
	String Type;
	
	
	
	Salle() {
		nbr_portes = 0;
		Type = null;
		composition = new int[largeur][hauteur];
		for(int i = 0; i<hauteur; i++) {
			for(int j = 0; j<largeur; j++) {
				composition[j][i] = 0;
			}
		}
		for(int i = 0; i<largeur; i++) {
			composition[i][0] = 1;
			composition[i][hauteur - 1] = 1;
		}
		for(int i = 0; i<hauteur; i++) {
			composition[0][i] = 1;
			composition[largeur - 1][i] = 1;
		}
		Random r = new Random();
		nb_ennemis = r.nextInt(6);
	}
	
	void placer_ennemis() {
		Random r = new Random();
		
		
	}
	
	void print_salle() {
		for(int i = 0; i<hauteur; i++) {
			for(int j = 0; j<largeur; j++) {
				System.out.print(composition[j][i]);
			}
			System.out.print("\n");
		}
	}
	
	
	
	
	Salle(int nbr_de_portes) {
		nord = null;
		sud = null;
		est = null;
		ouest = null;
		nbr_portes = nbr_de_portes;
		Type = "Normale";
		composition = new int[1000][1000];
	}
		
	Salle(int nbr_de_portes, String type) {
		nord = null;
		sud = null;
		est = null;
		ouest = null;
		nbr_portes = nbr_de_portes;
		Type = type;
		composition = new int[1000][1000];
	}
	
	void Ajouter_portes(int nbr_de_portes) {
		for (int i = 0; i < nbr_de_portes; i++) {
			Ajouter_une_porte();
		}
	}
	
	void Ajouter_une_porte() {
		Random r = new Random();
		int j = r.nextInt(3);
		switch (j) {
			case 0 :
				if (nord == null) {
					nord = new Porte(this);
				}
				else {
					Ajouter_une_porte();
				}
				
			case 1 :
				if (est == null) {
					est = new Porte(this);
				}
				else {
					Ajouter_une_porte();
				}
			case 2 :
				if (sud == null) {
					sud = new Porte(this);
				}
				else {
					Ajouter_une_porte();
				}
			case 3 :
				if (ouest == null) {
					ouest = new Porte(this);
				}
				else {
					Ajouter_une_porte();
				}
		}
	}
	
	
	
	void Lier_deux_Salles(Salle salle) {
		
	}
	
	public static void main(String args[]) throws Exception {
		Salle s = new Salle();
		s.print_salle();
	}

}
