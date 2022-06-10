package info3.game;


public class Etage {

	Salle[][] Salles;
	int[][] Coo_Indispo;
	int Niveau;
	
	Etage(int niveau) {
		Salles = new Salle[100][100];
		Coo_Indispo = new int[0][0];
		Niveau = niveau;
	}
	
	public void test_etage() throws Exception {
		try {
			System.out.println("Test création map");
			this.creer_map_simple_pour_test(4);
			this.afficher_etage();
			} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}
	
	
	public void initialisation_etage() {
		this.Salles[50][50] = new Salle(0, 0, "Entrée ");
	}
	
	public void creer_map_simple_pour_test(int niveau) {
		int i, j;
		initialisation_etage();
		for (i = 0; i < niveau/2; i++) {
			this.Salles[i+1][0] = new Salle(i+1, 0, "Normale");
			this.Salles[i+1][0].Ajouter_Porte_deux_sens('O', Salles[i][0]);
		}
		for (j = 0; j < niveau/2; j++) {
			this.Salles[i][j+1] = new Salle(i, j+1, "Normale");
			this.Salles[i][j+1].Ajouter_Porte_deux_sens('S', Salles[i][j]);
		}
		for (i = niveau/2; i < niveau; i++) {
			this.Salles[i+1][j] = new Salle(i+1, j, "Normale");
			this.Salles[i+1][j].Ajouter_Porte_deux_sens('O', Salles[i][j]);
		}
		
		this.Salles[i][j] = new Salle(i, j, " Boss  ");
		this.Salles[i][j].Ajouter_Porte_deux_sens('O', Salles[i][j]);
	}
	
	public void afficher_etage() {
		String porte_horiz_double = " <---> ";
		String porte_horiz_O = " <---- ";
		String porte_horiz_E = " ----> ";
		String porte_verti_double = "   I   ";
		String porte_verti_N = "   T   ";
		String porte_verti_S = "   L   ";
		
		
		for (int x = -6; x<6; x++) {
			String ligne = new String();
			ligne = "";
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
		}
	}

}

