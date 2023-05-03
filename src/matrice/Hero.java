package matrice;

public class Hero {

	private String nom;

	private Ressource[] stockBle = new Ressource[13];

	private Ressource[] stockBois = new Ressource[6];

	private Ressource[] stockPierre = new Ressource[4];

	private ObjetManufacture farine;

	private ObjetManufacture feu;

	private int poidsEmporte;

	private int partiesJouees;

	private Paire<Integer, Integer> caseCourante;

	private Ressource[][] map;

	private Paire<Integer, Integer>[] coordBoisSaved = new Paire[5];
	
	private int nbDeplacements;

	public Hero(String nom) {

		this.nom = nom;

		poidsEmporte = 0;

		partiesJouees = 0;

		caseCourante = new Paire<>(0, 0);

		for (Ressource ressource : stockBle) {

			ressource = null;

		}

		for (Ressource ressource : stockBois) {

			ressource = null;

		}

		for (Ressource ressource : stockPierre) {

			ressource = null;

		}

		for (Paire coord : coordBoisSaved) {

			coord = null;

		}

	}

	public String getNom() {

		return this.nom;

	}

	public Ressource[] getStockBlé() {

		return this.stockBle;

	}

	public Ressource[] getStockBois() {

		return this.stockBois;

	}

	public Ressource[] getStockPierre() {

		return this.stockPierre;

	}

	public ObjetManufacture getFarine() {

		return this.farine;

	}

	public ObjetManufacture getFeu() {

		return this.feu;

	}

	public int getPoidsEmporté() {

		return this.poidsEmporte;

	}

	public int getPartiesJouées() {

		return this.partiesJouees;

	}

	public void incrémenterPartiesJouées() {

		this.partiesJouees++;

	}

	public boolean peutEmporterPlus() {

		return this.poidsEmporte < 13;

	}

	public boolean ajouterRessource(Ressource ressource) {

		if (peutEmporterPlus()) {

			if (ressource instanceof Ble) {

				for (int i = 0; i < this.stockBle.length; i++) {

					if (this.stockBle[i] == null) {

						this.stockBle[i] = ressource;

						break;

					}

				}

			} else if (ressource instanceof Bois) {

				for (int i = 0; i < this.stockBois.length; i++) {

					if (this.stockBois[i] == null) {

						this.stockBois[i] = ressource;

						break;

					}

				}

			} else if (ressource instanceof Pierre) {

				for (int i = 0; i < this.stockPierre.length; i++) {

					if (this.stockPierre[i] == null) {

						this.stockPierre[i] = ressource;

						break;

					}

				}

			} else {

				return false; // Type de ressource inconnu

			}

			this.poidsEmporte += ressource.poids;

			return true;

		} else {

			return false; // Capacité d'emport dépassée

		}

	}

	private void prendre() {

		if (map[caseCourante.getPositionX()][caseCourante.getPositionY()] != null) {

			if (ajouterRessource(map[caseCourante.getPositionX()][caseCourante.getPositionY()]) == true) {

				map[caseCourante.getPositionX()][caseCourante.getPositionY()] = null;

			} else {

				System.out.println("Il n'y a rien ici");

			}

		}

	}

	public boolean fairePain() {

		if (farine == null && feu == null) {

			System.out.println("Il vous manque le feu ou la farine pour faire du pain");

			return false;

		} else {

			System.out.println("Vous avez fait du pain");

			return true;

		}

	}

	/*----------------------------------------------------------------------------------------------*/

	private void seDeplacer(Direction direction) {

		switch (direction) {

		case HAUT:

			caseCourante.setPositionY(caseCourante.getPositionY() - 1);

			break;

		case BAS:

			caseCourante.setPositionY(caseCourante.getPositionY() + 1);

			break;

		case GAUCHE:

			caseCourante.setPositionX(caseCourante.getPositionX() - 1);

			break;

		case DROITE:

			caseCourante.setPositionX(caseCourante.getPositionX() + 1);

			break;

		default:

			break;

		}

	}

	/*----------------------------------------------------------------------------------------------*/

	public boolean verifierStock(Ressource[] ressource, int nbrAttendu) {

		int nbrRessource = 0;

		for (int i = 0; i < ressource.length; i++) {

			if (ressource[i] != null) {

				nbrRessource++;

			}

		}

		if (ressource[0] instanceof Ble) {

			//System.out.println(nbrRessource);

		}

		if (nbrRessource >= nbrAttendu) {

			return true;

		} else {

			return false;

		}

	}

	/*----------------------------------------------------------------------------------------------*/

	public int jouer(Ressource[][] map) {

		this.map = map;

		parcoursMap();

		return nbDeplacements;

	}

	/*----------------------------------------------------------------------------------------------*/

	public int parcoursMap() {

		for (int y = 0; y < 10; y++) {

			if (caseCourante.getPositionX() == 0) {

				for (int x = 0; x <= 9; x++) {

					ramasserBle();

					ramasserPierre();

					saveCoordBois();

					if (x != 9) {

						seDeplacer(Direction.DROITE);

					}

				}

			} else {

				for (int x = 9; x >= 0; x--) {

					ramasserBle();

					ramasserPierre();

					saveCoordBois();

					if (x != 0) {

						seDeplacer(Direction.GAUCHE);

					}

				}

			}

			if (y < 9) {

				seDeplacer(Direction.BAS);

			}

		}


		// Aller chercher les Bois
		for (int i = 0; i < coordBoisSaved.length; i++) {

			// On défini donc ou on va
			int caseDestinationX = coordBoisSaved[i].getPositionX();
			int caseDestinationY = coordBoisSaved[i].getPositionY();
			

			// On se déplace jusqu'à caseDestination en X puis Y
			// On déplace les X
			while (caseCourante.getPositionX() != caseDestinationX) {
				if (caseCourante.getPositionX() > caseDestinationX) {
					seDeplacer(Direction.GAUCHE);
				} else {
					seDeplacer(Direction.DROITE);
				}
				nbDeplacements++;
			}
			
			
			// On déplace Y
			while (caseCourante.getPositionY() != caseDestinationY) {
				if (caseCourante.getPositionY() > caseDestinationY) {
					seDeplacer(Direction.HAUT);
				} else {
					seDeplacer(Direction.BAS);
				}
				nbDeplacements++;
			}

			// On prend l'objet sur la case
			prendre();
			System.out.println("Test");

		}
		
		feu = ((Bois)stockBois[0]).utiliser(stockBois);
		
		fairePain();
		
		// On va en 9x9
		int caseDestinationX = 9;
		int caseDestinationY = 9;

		// On se déplace jusqu'à caseDestination en X puis Y
		// On déplace les X
		while (caseCourante.getPositionX() != caseDestinationX) {
			if (caseCourante.getPositionX() > caseDestinationX) {
				seDeplacer(Direction.GAUCHE);
			} else {
				seDeplacer(Direction.DROITE);
			}
			nbDeplacements++;
		}

		// On déplace Y
		while (caseCourante.getPositionY() != caseDestinationY) {
			if (caseCourante.getPositionY() > caseDestinationY) {
				seDeplacer(Direction.HAUT);
			} else {
				seDeplacer(Direction.BAS);
			}
			nbDeplacements++;
		}
		
		System.out.println("Final : " + caseCourante.getPositionX() + " " + caseCourante.getPositionY());

		if (caseCourante.getPositionX() == 9 && caseCourante.getPositionY() == 9) {
			return nbDeplacements;
		} else {
			return 0;
		}

	}

	/*----------------------------------------------------------------------------------------------*/

	public void ramasserBle() {

		if (verifierStock(stockBle, 10) == false
				&& map[caseCourante.getPositionX()][caseCourante.getPositionY()] instanceof Ble) {

			prendre();

			System.out.println("g");

		}

	}

	public void ramasserPierre() {

		if (verifierStock(stockPierre, 1) == false
				&& map[caseCourante.getPositionX()][caseCourante.getPositionY()] instanceof Pierre) {

			prendre();

		}

	}

	public void saveCoordBois() {

		if (map[caseCourante.getPositionX()][caseCourante.getPositionY()] instanceof Bois) {

			for (int i = 0; i < coordBoisSaved.length; i++) {

				if (coordBoisSaved[i] == null) {

					coordBoisSaved[i] = new Paire(caseCourante.getPositionX(), caseCourante.getPositionY());
					break;

				}

			}

		}

	}

	/*----------------------------------------------------------------------------------------------*/

	public void afficherMap() {

		System.out.println(caseCourante.getPositionX() + " " + caseCourante.getPositionY());

		for (int x = 0; x < 10; x++) {

			for (int j = 0; j < 10; j++) {

				if (caseCourante.getPositionX() == j && caseCourante.getPositionY() == x) {

					System.out.print("1 ");

				} else {

					System.out.print('0' + " ");

				}

			}

			System.out.println(" ");

		}

		System.out.println(" ");

		System.out.println(" ");

	}

}