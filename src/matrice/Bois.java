package matrice;

public class Bois extends Ressource implements Utilisable {
	
	private int poids = 2;
	
	// Constructor
	public Bois(String id) {
		super(id);
	}
	
	// Methods
	
	/*
	 * La méthode utiliser de Bois prend un tableau de 5 bois en paramètre et retourne un objet
	   ObjetManufacture de type Feu.
	 * */
	public ObjetManufacture utiliser(Ressource[] r) {
		int nbrDeBois = 0;
		
		// Vérification que le joueur a 5 bois dans son inventaire
		for(int i = 0; i < 6; i++) {
			if(r[i] != null) {
				nbrDeBois++;
			}
		}
		
		// S'il en a au moins 5
		if(nbrDeBois >= 5) {
			
			// On parcours le tableau pour retirer les 5 derniers bois
			for(int i = 5; i == 0; i--) {
				
				if(nbrDeBois == nbrDeBois - 5) {
					break;
				}
				
				if(r[i] != null) {
					r[i] = null;
				}
			}
			
			// Return
			ObjetManufacture feu = new ObjetManufacture("Feu");
			return feu;
		}
		else {
			System.out.println("Vous n'avez pas assez de bois pour faire du feu");
			return null;
		}
		
		
	}
	
}
