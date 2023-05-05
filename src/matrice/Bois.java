package matrice;

public class Bois extends Ressource implements Utilisable {
	
	// Attributs
	private int poids = 2;
	
	// Constructor
	public Bois(String id) {
		super(id);
	}
	
	
	// Methods
	
	
	/***
	 * 
	 * @param r : tableau de ressources
	 * @return : retourne un objet de type ObjetManufacture "Feu" si le joueur a les ressource nécéssaires
	 */
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
			for(int y = 5; y >= 0; y--) {
				
				if(nbrDeBois ==  0) {
					break;
				}
				
				if(r[y] != null) {
					r[y] = null;
					nbrDeBois--;
				}
			}
			
			
			// Return l'objet Feu
			ObjetManufacture feu = new ObjetManufacture("Feu");
			System.out.println("Vous avez fait du feu");
			return feu;
		}
		// Sinon affiche une erreur et renvoie null
		else {
			System.out.println("Vous n'avez pas assez de bois pour faire du feu");
			return null;
		}
		
		
	}
	
}
