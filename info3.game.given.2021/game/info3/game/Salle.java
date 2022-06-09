package info3.game;

import java.util.Random;

class Salle {
	
	Porte nord;
	Porte sud;
	Porte est;
	Porte ouest;
	
	int nbr_portes;
	
	int[][] composition;
	
	String Type;
	
	Salle() {
		nord = null;
		sud = null;
		est = null;
		ouest = null;
		nbr_portes = 0;
		Type = null;
		composition = new int[1000][1000];
	}
	
	Salle(int nbr_de_portes) {
		nbr_portes = 0;
		Ajouter_portes(nbr_de_portes);
		Type = "Normale";
		composition = new int[1000][1000];
	}
	
	Salle(String type) {
		nord = null;
		sud = null;
		est = null;
		ouest = null;
		nbr_portes = 0;
		Type = type;
		composition = new int[1000][1000];
	}
		
	Salle(int nbr_de_portes, String type) {
		nbr_portes = 0;
		Ajouter_portes(nbr_de_portes);
		Type = type;
		composition = new int[1000][1000];
	}
	
	void Ajouter_portes(int nbr_de_portes) {
		for (int i = 0; i < nbr_de_portes; i++) {
			boolean v = Ajouter_une_porte();
			if (v) {nbr_portes++;}
		}
	}
	
	boolean Ajouter_une_porte() {	//Ajoute une porte sur un des côtés, renvoie false si aucune porte n'a pu être ajoutée
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
	
	
	boolean Lier_deux_Salles(Salle salle) {
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
	
	void Lier_deux_Salles(Salle salle, Porte origine, Porte destination) {
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

}
