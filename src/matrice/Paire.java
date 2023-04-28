package matrice;

public class Paire<X, Y> {
	
	private X positionX;
	private Y positionY;
	
	public Paire(X valeurGauche, Y valeurDroite) {
		this.positionX=valeurGauche;
		this.positionY= valeurDroite;
		
	}
	
	public X getPositionX() {
		return positionX;
	}
	
	public Y getPositionY() {
		return positionY;
	}
	
	public void setPositionX(X position) {
		positionX = position;
	}
	
	public void setPositionY(Y position) {
		positionY = position;
	}
	
	public String toString() {
		return positionX + "" + positionY;
	}
			
}
