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
		
		// Vérification que le joueur a 5 bois dans son inventaire
		for(int i = 0; i < 6; i++) {
			if(r[i] instanceof Bois) {
				
			}
		}
		
		
		ObjetManufacture feu = new ObjetManufacture("Feu");
		return feu;
	}
	
}
