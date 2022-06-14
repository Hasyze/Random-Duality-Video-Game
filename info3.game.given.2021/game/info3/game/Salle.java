package info3.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	
	
	int largeur = 50;
	int hauteur = 50;
	int nb_ennemis = 0;
	
	String Type;
	
	void print_salle() {
		for(int i = 0; i<hauteur; i++) {
			for(int j = 0; j<largeur; j++) {
				System.out.print(composition[j][i]);
			}
			System.out.print("\n");
		}
	}
	
	
	
	
	Salle(int nbr_de_portes) {
		nbr_portes = 0;
		Ajouter_portes(nbr_de_portes);
		Type = "Normale";
		composition = new int[50][50];
	}
	
	Salle(String type) {
		nord = null;
		sud = null;
		est = null;
		ouest = null;
		nbr_portes = 0;
		Type = type;
		composition = new int[50][50];
	}
		
	Salle(int nbr_de_portes, String type) {
		nbr_portes = 0;
		Ajouter_portes(nbr_de_portes);
		Type = type;
		composition = new int[50][50];
	}
	
	void Ajouter_portes(int nbr_de_portes) {	//Ajoute pls portes
		for (int i = 0; i < nbr_de_portes; i++) {
			boolean v = Ajouter_une_porte();
			if (v) {nbr_portes++;}
		}
	}
	
	boolean Ajouter_une_porte() {	//Ajoute une porte sur un des côtés (Aléatoire), renvoie false si aucune porte n'a pu être ajoutée
		Random r = new Random();
		int j = r.nextInt(4);
		switch (j) {
			case 0 :
				if (nord == null) {
					nord = new Porte(this);
					return true;
				}
				else {
					if (sud == null) {
						sud = new Porte(this);
						return true;
					}
					else {
						if (est == null) {
							est = new Porte(this);
							return true;
						}
						else {
							if (ouest == null) {
								ouest = new Porte(this);
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
				
			case 1 :
				if (sud == null) {
					sud = new Porte(this);
					return true;
				}
				else {
					if (ouest == null) {
						ouest = new Porte(this);
						return true;
					}
					else {
						if (nord == null) {
							nord = new Porte(this);
							return true;
						}
						else {
							if (est == null) {
								est = new Porte(this);
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
			case 2 :
				if (est == null) {
					est = new Porte(this);
					return true;
				}
				else {
					if (ouest == null) {
						ouest = new Porte(this);
						return true;
					}
					else {
						if (nord == null) {
							nord = new Porte(this);
							return true;
						}
						else {
							if (sud == null) {
								sud = new Porte(this);
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
			default :
				if (ouest == null) {
					ouest = new Porte(this);
					return true;
				}
				else {
					if (est == null) {
						est = new Porte(this);
						return true;
					}
					else {
						if (sud == null) {
							sud = new Porte(this);
							return true;
						}
						else {
							if (nord == null) {
								nord = new Porte(this);
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
		}
	}
	
	boolean Retirer_Portes(Porte porte) {	//Retire la porte si elle est présente, retourne true si fait, false sinon
		if (nord == porte) {
			nord = null;
			nbr_portes--;
			return true;
		}
		if (est == porte) {
			est = null;
			nbr_portes--;
			return true;
		}
		if (sud == porte) {
			sud = null;
			nbr_portes--;
			return true;
		}
		if (ouest == porte) {
			ouest = null;
			nbr_portes--;
			return true;
		}
		return false;
	}
	
	
	boolean Lier_deux_Salles(Salle salle) {		//renvois true si on lie deux salles en recherchant des portes disponible, false sinon
		Porte P1 = this.Trouver_porte_disponible();
		Porte P2 = salle.Trouver_porte_disponible();
		if ((P1 == null) || (P2 == null)) {
			return false;
		}
		else {
			P1.salle_destination = salle;
			P2.salle_destination = this;
			return true;
		}
		
	}
	
	void Lier_deux_Salles(Salle salle, Porte origine, Porte destination) {	//On lie deux salles en choisissant les portes qui font le lien
		origine.salle_destination = salle;
		destination.salle_destination = this;
	}
	
	boolean Porte_non_liees() {	//Renvoie true si il reste des portes non liées, false sinon
		int compteur = 0;
		if ((nord != null) && (nord.salle_destination != null)) {
			compteur++;
		}
		if ((sud != null) && (sud.salle_destination != null)) {
			compteur++;
		}
		if ((est != null) && (est.salle_destination != null)) {
			compteur++;
		}
		if ((ouest != null) && (ouest.salle_destination != null)) {
			compteur++;
		}
		return (compteur != nbr_portes);
	}
	
	Porte Trouver_porte_disponible() {	//On cherche une portes dispo mais sans les verifier tjrs dans le même ordre pour rajouter de l'aléatoire
		Random r = new Random();
		int j = r.nextInt(4);
		switch (j) {
			case 0 :
				if ((nord != null) && (nord.salle_destination == null)) {
					return nord;
				}
				if ((sud != null) && (sud.salle_destination == null)) {
					return sud;
				}
				if ((est != null) && (est.salle_destination == null)) {
					return est;
				}
				if ((ouest != null) && (ouest.salle_destination == null)) {
					return ouest;
				}
				else {
					return null;
				}
				
			case 1 :
				if ((sud != null) && (sud.salle_destination == null)) {
					return sud;
				}
				if ((ouest != null) && (ouest.salle_destination == null)) {
					return ouest;
				}
				
				if ((nord != null) && (nord.salle_destination == null)) {
					return nord;
				}
				if ((est != null) && (est.salle_destination == null)) {
					return est;
				}
				else {
					return null;
				}
			case 2 :
				if ((est != null) && (est.salle_destination == null)) {
					return est;
				}
				if ((ouest != null) && (ouest.salle_destination == null)) {
					return ouest;
				}
				if ((nord != null) && (nord.salle_destination == null)) {
					return nord;
				}
				if ((sud != null) && (sud.salle_destination == null)) {
					return sud;
				}
				else {
					return null;
				}
			default :
				if ((ouest != null) && (ouest.salle_destination == null)) {
					return ouest;
				}
				if ((est != null) && (est.salle_destination == null)) {
					return est;
				}
				if ((sud != null) && (sud.salle_destination == null)) {
					return sud;
				}
				if ((nord != null) && (nord.salle_destination == null)) {
					return nord;
				}
				else {
					return null;
				}
		} 
	}
	
	void set_compo(String plan) {
		try
        {
            File fil = new File(plan);
            BufferedReader br = new BufferedReader(new FileReader(fil));
            System.out.println("file content: ");
            int c = 0, l = 0;
            int r=0;
            while((r=br.read())!=-1)
            {
            	if ((r == 48) || (r == 49) || (r == 50) || (r == 51) || (r == 52)) {
	            	this.composition[l][c] = r;
	                c++;
	                if (c >= 50) {
	                	c = 0;
	                	l++;
	                }
            	}
                System.out.print((char)r);
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

}
