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
    
    public Hero(String nom, Ressource[] stockBlé, Ressource[] stockBois, Pierre pierre, ObjetManufacture farine, ObjetManufacture feu) {
        this.nom = nom;
        this.stockBle = stockBle;
        this.stockBois = stockBois;
        this.stockPierre = stockPierre;
        this.farine = farine;
        this.feu = feu;
        this.poidsEmporte = 0;
        this.partiesJouees = 0;
        this.caseCourante = caseCourante ;
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
        this.caseCourante = caseCourante;
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
                    if (this.stockBois[i] == null) {
                        this.stockBois[i] = ressource;
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
}