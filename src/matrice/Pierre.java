package matrice;

public class Pierre extends Ressource {
	
private int poids = 3;
	
	// Constructor
	public Pierre(String id) {
		super(id);
	}
	
	// Methods
	
	public ObjetManufacture utiliser(Ressource[] r) {
		int nbrDeBle = 0;
		
		// Vérification que le joueur a 10 blés dans son inventaire
		for(int i = 0; i < 13; i++) {
			if(r[i] != null) {
				nbrDeBle++;
			}
		}
		
		// S'il en a au moins 10
		if(nbrDeBle >= 10) {
			
			// On parcours le tableau pour retirer les 10 derniers blés
			for(int i = 12; i >= 0; i--) {
				
				if(nbrDeBle == 0) {
					break;
				}
				
				if(r[i] != null) {
					r[i] = null;
					nbrDeBle--;
				}
			}
			
			// Return
			ObjetManufacture farine = new ObjetManufacture("Farine");
			System.out.println("Vous avez fait de la farine");
			return farine;
		}
		else {
			System.out.println("Vous n'avez pas assez de blé pour faire de la farine");
			return null;
		}
		
		
	}
	
}
