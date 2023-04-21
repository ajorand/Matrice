package matrice;

public class Hero{
    private String nom;
    private Ressource[] stockBle = new Ressource[13] ;
    private Ressource[] stockBois = new Ressource[6];
    private Ressource[] stockPierre = new Ressource[4];	
    private ObjetManufacture farine;
    private ObjetManufacture feu;
    private int poidsEmporte;
    private int partiesJouees;
    private int[] caseCourante =  new int[2];
    
    public Hero(String nom) {
        this.nom = nom;
    
        this.farine = farine;
        
        poidsEmporte = 0;
        partiesJouees = 0;
        caseCourante[0] = 0;
        caseCourante[1] = 0;
        
        for(Ressource ressource: stockBle) {
        	ressource = null;
        }
        for(Ressource ressource: stockBois) {
        	ressource = null;
        }
        for(Ressource ressource: stockPierre) {
        	ressource = null;
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
    
    public int[] getCaseCourante() {
        return this.caseCourante;
    }
    
    public void setCaseCourante(int x, int y) {
        this.caseCourante[0] = x;
        this.caseCourante[1] = y;
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
            }else {
                return false; // Type de ressource inconnu
            }
            this.poidsEmporte += ressource.poids;
            return true;
        } else {
            return false; // Capacité d'emport dépassée
        }
    }
    
    public int jouer(Ressource[][] map) {
    	int nbDeplacements = 0;
    	
    	return nbDeplacements;
    }
    
}