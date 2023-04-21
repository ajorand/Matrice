package matrice;


public class Hero{
    private String nom;
    private Ressource[] stockBle = new Ressource[10] ;
    private Ressource[] stockBois = new Ressource[5];
    private Pierre pierre;
    private ObjetManufacture farine;
    private ObjetManufacture feu;
    private int poidsEmporte;
    private int partiesJouees;
    private Pair<Integer, Integer> caseCourante;
    
    public Hero(String nom, Ressource[] stockBlé, Ressource[] stockBois, Pierre pierre, ObjetManufacture farine, ObjetManufacture feu) {
        this.nom = nom;
        this.stockBle = stockBle;
        this.stockBois = stockBois;
        this.pierre = pierre;
        this.farine = farine;
        this.feu = feu;
        this.poidsEmporte = 0;
        this.partiesJouees = 0;
        this.caseCourante = new Pair<Integer, Integer>(0, 0);
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
    
    public Pierre getPierre() {
        return this.pierre;
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
    
    public Pair<Integer, Integer> getCaseCourante() {
        return this.caseCourante;
    }
    
    public void setCaseCourante(int x, int y) {
        this.caseCourante = new Pair<Integer, Integer>(x, y);
    }
    
    public void incrémenterPartiesJouées() {
        this.partiesJouees++;
    }
    
    public boolean peutEmporterPlus() {
        return this.poidsEmporte < 13;
    }
    
    public boolean ajouterRessource(Ressource ressource) {
        if (peutEmporterPlus()) {
            if (ressource instanceof Blé) {
                for (int i = 0; i < this.stockBle.length; i++) {
                    if (this.stockBle[i] == null) {
                    	this.stockBle[i] = ressource;
                        break;
                    }
                }
            } else if (ressource instanceof Bois) {
                for (int i = 0; i < stockDeBois.length; i++) {
                    if (this.stockBois[i] == null) {
                        this.stockBois[i] = ressource;
                        break;
                    }
                }
            } else {
                return false; // Type de ressource inconnu
            }
            this.poidsEmporte += ressource.getPoids();
            return true;
        } else {
            return false; // Capacité d'emport dépassée
        }
    }
}