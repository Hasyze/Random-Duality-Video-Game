package Map;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Entities.Porte;
import Entities.Mur;
import Entities.Rocher;
import Entities.Ennemis;
import info3.game.EntityManager;

public class Salle {

	Porte[] portes;
	/*
	 * Stock les portes de la salle. Leur indice correspond à leur orientation : 0 =
	 * Nord 1 = Est 2 = Sud 3 = Ouest
	 */

	int nbr_portes; // NOmbre de porte que possède la salle

	int[][] compo;
	/*
	 * Pour la composition, tableau en 2D -0 Vide -1 Mur -2 Porte -3 Rocher ? -4
	 * Ennemis ? - -9 Zone "chemin" ? Faire differents layers
	 */

	int largeur = 50; // Nombre de bloc sur le coté de la salle.
	int hauteur = 50;

	boolean salle_vide; // =false tant que le joueur n'a pas tué tout les ennemis.

	String type; // Type de la salle (Normale, Boss, Entrée, etc...)

	Image background; // Fond d'ecran de la salle

	//** INITIALISATION SALLE **//
	
	Salle(int nbr_de_portes, String type) throws IOException {

		this.portes = new Porte[4];
		this.nbr_portes = 0;
		this.compo = new int[50][50];
		this.type = type;
		this.background = this.init_background();

		this.Ajouter_portes(nbr_de_portes);
		this.set_compo(type);
	}

	void set_compo(String type) {
		String path;
		path = "resources/".concat(type).concat(".txt"); // On choisi le fichier pattern en fonction du type de la salle
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			int c = 0, l = 0;
			int r = 0;

			if (type == "normale") { // Si la salle est normale on choisi un des deux pattern aléatoirement en
										// sautant plus ou moins de ligne
				Random x = new Random();
				int j = x.nextInt(2);
				for (int i = 0; i < 2550 * j; i++) { // On saute 2550 caractères (nombre de caractère pour faire un
														// pattern)si j=1
					r = br.read();
				}
			}

			// On lit le pattern et on l'écrit dans compo[][]
			while (((r = br.read()) != -1) && (l < 50)) {
				System.out.print((char) r);
				if ((r == 48) || (r == 49) || (r == 50) || (r == 51) || (r == 52)) {
					this.compo[l][c] = r;
					c++;
					if (c >= 50) {
						c = 0;
						l++;
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Image init_background() throws IOException {
		Image[] background = new Image[2];
		background[0] = loadImage("resources/images_test/sprite_mur.png");
		background[1] = loadImage("resources/images_test/sprite_mur2.png");
		Random rand = new Random();
		int r = rand.nextInt(2);
		return background[r];
	}

	// Fonction pour récupérer une image
	public static Image loadImage(String filename) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		}
		return null;
	}

	void print_salle() {
		System.out.print("\n");
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				System.out.print(compo[j][i]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	//** CREATION ETAGE **//

	void Ajouter_portes(int nbr_de_portes) { // Ajoute plusieurs portes
		for (int i = 0; i < nbr_de_portes; i++) {
			boolean v = Ajouter_une_porte();
			if (v) {
				nbr_portes++;
			} // SI l'ajout à réussi, on incrémente nbr_portes
		}
	}

	boolean Ajouter_une_porte() { // Ajoute une porte sur un des côtés (Aléatoire), renvoie false si aucune porte
									// n'a pu être ajoutée
		// Une optimisation peut peut-être être possible

		// Ici on vérifie qu'il reste au moins un emplacement de porte non utilisé

		boolean emplacement_dispo = false;
		for (int i = 0; i < 4; i++) {
			if (portes[i] == null) {
				emplacement_dispo = true;
			}
		}
		if (emplacement_dispo == false) {
			return false;
		}

		// Ensuite on prend un emplacement random et on essai d'y mettre une porte. On
		// réessaye tant qu'on n'y arrive pas

		while (true) {
			Random r = new Random();
			int j = r.nextInt(4);
			if (portes[j] == null) {
				portes[j] = new Porte(this, j);
				return true;
			}
		}
	}

	boolean Porte_non_liees() { // Retourne true si la salle possède au moins une porte non liée

		boolean porte_dispo = false;
		for (int i = 0; i < 4; i++) {
			if (portes[i] != null) {
				if (portes[i].salle_destination == null) {
					porte_dispo = true;
				}
			}
		}
		return porte_dispo;
	}

	boolean Lier_deux_salles(Salle salle) { // renvois true si on lie deux salles en recherchant des portes disponible,
											// false sinon
		Porte P1 = this.Trouver_porte_disponible();
		Porte P2 = salle.Trouver_porte_disponible();
		if ((P1 == null) || (P2 == null)) {
			return false;
		} else {
			this.Lier_deux_salles(salle, P1, P2);
			return true;
		}

	}

	void Lier_deux_salles(Salle salle2, Porte P1, Porte P2) { // On lie les deux salles en choisissant les portes qui
																// feront le lien

		P1.salle_destination = salle2;
		P1.orientation_salle_destination = P2.orientation_salle_origine;

		P2.salle_destination = this;
		P2.orientation_salle_destination = P1.orientation_salle_origine;
	}

	boolean Retirer_Portes(Porte porte) { // Retire la porte si elle est présente, retourne true si fait, false sinon
		for (int i = 0; i < 4; i++) {
			if (portes[i] == porte) {
				portes[i] = null;
				nbr_portes--;
				return true;
			}
		}
		return false;

	}

	Porte Trouver_porte_disponible() { // On cherche une portes dispo aléatoirement, renvoie nul si aucune porte n'est
										// dispo

		if (Porte_non_liees() == false) { // On renvoie null si aucune porte n'est dispo
			return null;
		}

		while (true) { // On choisi un emplacement de porte random et on renvoie la porte si elle est
						// dispo, on recommence sinon
			Random r = new Random();
			int j = r.nextInt(4);
			if (portes[j] != null) {
				if (portes[j].salle_destination == null) {
					return portes[j];
				}
			}
		}

	}
	
	
	//** EN JEU **//
	
	void charger_entites_salle() {
		for (int i = 0; i<50; i++) {
			for (int j = 0; j<50; j++) {
				int x = compo[i][j];
				switch (x) {
				case 1 :
					EntityManager.EM_add(Mur(i*20, j*20));
				case 2 :
					if ( (i == 0) && (portes[0] != null) ) {
						EntityManager.EM_add(portes[0]);
					}
					if ( (j == 0) && (portes[3] != null) ) {
						EntityManager.EM_add(portes[3]);
					}
					if ( (i == 49) && (portes[2] != null) ) {
						EntityManager.EM_add(portes[2]);
					}
					if ( (j == 49) && (portes[1] != null) ) {
						EntityManager.EM_add(portes[1]);
					}
					else {
						EntityManager.EM_add(Mur(i*20, j*20));
					}
				case 3 :
					EntityManager.EM_add(Rocher(i*20, j*20));
				case 4 :
					EntityManager.EM_add(Ennemis(i*20, j*20));
				}
				
				
			}
		}
	}
	

}
