package info3.game;

import java.util.Random;

class Salle {	
	
	Porte nord;
	Porte sud;
	Porte est;
	Porte ouest;
	
	int nbr_portes;
	
	int[][] composition;
	int taillex;
	int tailley;
	
	String Type;
	
	
	
	Salle() {
		nbr_portes = 0;
		Type = null;
		composition = new int[32][18];
		for(int i = 0; i<18; i++) {
			for(int j = 0; j<32; j++) {
				composition[j][i] = 0;
			}
		}
		for(int i = 0; i<32; i++) {
			composition[i][0] = 1;
			composition[i][17] = 1;
		}
		for(int i = 0; i<18; i++) {
			composition[0][i] = 1;
			composition[31][i] = 1;
		}
	}
	
	void print_salle() {
		for(int i = 0; i<18; i++) {
			for(int j = 0; j<32; j++) {
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
