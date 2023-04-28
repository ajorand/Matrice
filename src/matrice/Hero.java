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
    private Paire<Integer, Integer> caseCourante;
    private Ressource[][] map;
 
    
    public Hero(String nom) {
        
    	this.nom = nom;
        
        poidsEmporte = 0;
        partiesJouees = 0;
        caseCourante = new Paire<>(0, 0);
        
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
    
    
    private void prendre() {
    
    	if(map[caseCourante.getPositionX()][caseCourante.getPositionY()] != null) {
    		if(ajouterRessource(map[caseCourante.getPositionX()][caseCourante.getPositionY()]) == true) {
    			map[caseCourante.getPositionX()][caseCourante.getPositionY()] = null;
    		}
    		else {
    			System.out.println("Il n'y a rien ici");
    		}
    	}
    }
    
    
    public boolean fairePain() {
    	if(farine == null && feu == null) {
    		System.out.println("Il vous manque le feu ou la farine pour faire du pain");
    		return false;
    	}
    	else {
    		System.out.println("Vous avez fait du pain");
    		return true;
    	}
    }
    
    
    private void seDeplacer(Direction direction) {
    	
    	switch (direction) {
    	  case HAUT:
    	  	  caseCourante.setPositionY(caseCourante.getPositionY() + 1);
    	      break;
    	  case BAS:
    		  caseCourante.setPositionY(caseCourante.getPositionY() - 1);
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
    
    public boolean verifierStock(Ressource[] ressource, int nbrAttendu) {
		int nbrRessource = 0;
    	for(int i = 0; i < ressource.length; i++) {
			if(ressource[i] != null) {
				nbrRessource++;
			}
		}
    	
    	if(nbrRessource >= nbrAttendu) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public int jouer(Ressource[][] map) {
    	
    	// Attributs et initialisation
    	this.map = map;
    	int nbDeplacements = 0;
    	
    	
    	// On récupère les 5 bois
    	
    	while(verifierStock(stockBois, 5) == false) {
    		
    		// Etape 1 : matrice de couts
        	// Initialisation de la matrice en fonction de la case Courante
        	int[][] matriceDeCouts = new int[10][10];
        	
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			matriceDeCouts[x][y] = Math.abs(caseCourante.getPositionX() - x) + Math.abs(caseCourante.getPositionY() - y);	
        		}
        	}
        
        	
        	// Case sans bois cout a 100
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			if(map[x][y] instanceof Bois) {}
        			else {
        				matriceDeCouts[x][y] = 100;
        			}
        		}
        	}
        	
        	// Case courante cout a 100
        	matriceDeCouts[caseCourante.getPositionX()][caseCourante.getPositionY()] = 99;
        	
      
        	
        	// On défini donc ou on va
        	int caseDestinationX = 0;
        	int caseDestinationY = 0;
        	int min = 50;
        	
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			if(matriceDeCouts[x][y] < min) {
        				caseDestinationX = x;
        				caseDestinationY = y;
        				min = matriceDeCouts[x][y];
        			}
        		}
        	}
        	
        	
        	// On se déplace jusqu'à caseDestination en X puis Y
        	// On déplace les X
        	while(caseCourante.getPositionX() != caseDestinationX) {
        		if(caseCourante.getPositionX() > caseDestinationX) {
        			seDeplacer(Direction.GAUCHE);
        		}
        		else {
        			seDeplacer(Direction.DROITE);
        		}
        		nbDeplacements++;
        	}
        	
        	// On déplace Y
			while(caseCourante.getPositionY() != caseDestinationY) {
				if(caseCourante.getPositionY() > caseDestinationY) {
					seDeplacer(Direction.BAS);
				}
				else {
					seDeplacer(Direction.HAUT);
				}
				nbDeplacements++;
			}
        	
        	
        	// On prend l'objet sur la case
        	prendre();
        	
        	
    	}
    	
    	feu = ((Bois)stockBois[0]).utiliser(stockBois);
    	
    	
    	
    	
    	
    	/*
    	 * Pierre
    	 * */
    	while(verifierStock(stockPierre, 1) == false) {
    		
    		// Etape 1 : matrice de couts
        	// Initialisation de la matrice en fonction de la case Courante
        	int[][] matriceDeCouts = new int[10][10];
        	
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			matriceDeCouts[x][y] = Math.abs(caseCourante.getPositionX() - x) + Math.abs(caseCourante.getPositionY() - y);	
        		}
        	}
        	
        	
        	// Case courante cout a 100
        	matriceDeCouts[caseCourante.getPositionX()][caseCourante.getPositionY()] = 100;
        	
        	// Case sans ble cout a 100
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			if(map[x][y] instanceof Pierre) {}
        			else {
        				matriceDeCouts[x][y] = 100;
        			}
        		}
        	}
        	
        	// On défini donc ou on va
        	int caseDestinationX = 0;
        	int caseDestinationY = 0;
        	int min = 50;
        	
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			if(matriceDeCouts[x][y] < min) {
        				caseDestinationX = x;
        				caseDestinationY = y;
        				min = matriceDeCouts[x][y];
        			}
        		}
        	}
        	
        	
        	// On se déplace jusqu'à caseDestination en X puis Y
        	// On déplace les X
        	while(caseCourante.getPositionX() != caseDestinationX) {
        		if(caseCourante.getPositionX() > caseDestinationX) {
        			seDeplacer(Direction.GAUCHE);
        		}
        		else {
        			seDeplacer(Direction.DROITE);
        		}
        		nbDeplacements++;
        	}
        	
        	// On déplace Y
			while(caseCourante.getPositionY() != caseDestinationY) {
				if(caseCourante.getPositionY() > caseDestinationY) {
					seDeplacer(Direction.BAS);
				}
				else {
					seDeplacer(Direction.HAUT);
				}
				nbDeplacements++;
			}
        	
        	
        	// On prend l'objet sur la case
        	prendre();
        	
    	}
    	
    	
    	
    	/*
    	 * Ble
    	 * */
    	while(verifierStock(stockBle, 10) == false) {
    		
    		// Etape 1 : matrice de couts
        	// Initialisation de la matrice en fonction de la case Courante
        	int[][] matriceDeCouts = new int[10][10];
        	
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			matriceDeCouts[x][y] = Math.abs(caseCourante.getPositionX() - x) + Math.abs(caseCourante.getPositionY() - y);	
        		}
        	}
        	
        	
        	// Case sans ble cout a 100
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			if(map[x][y] instanceof Ble) {}
        			else {
        				matriceDeCouts[x][y] = 1000;
        			}
        		}
        	}
        	
        	// Case courante cout a 100
        	matriceDeCouts[caseCourante.getPositionX()][caseCourante.getPositionY()] = 1000;
        	
        	/*
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			System.out.print(matriceDeCouts[x][y] + " ");
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	System.out.println(" ");
        	*/
        	
        	// On défini donc ou on va
        	int caseDestinationX = 0;
        	int caseDestinationY = 0;
        	int min = 500;
        	
        	for(int x = 0; x < 10; x++) {
        		for(int y = 0; y < 10; y++) {
        			if(matriceDeCouts[x][y] < min) {
        				caseDestinationX = x;
        				caseDestinationY = y;
        				min = matriceDeCouts[x][y];
        			}
        		}
        	}
        	
        	
        	// On se déplace jusqu'à caseDestination en X puis Y
        	// On déplace les X
        	while(caseCourante.getPositionX() != caseDestinationX) {
        		if(caseCourante.getPositionX() > caseDestinationX) {
        			seDeplacer(Direction.GAUCHE);
        		}
        		else {
        			seDeplacer(Direction.DROITE);
        		}
        		nbDeplacements++;
        	}
        	
        	// On déplace Y
			while(caseCourante.getPositionY() != caseDestinationY) {
				if(caseCourante.getPositionY() > caseDestinationY) {
					seDeplacer(Direction.BAS);
				}
				else {
					seDeplacer(Direction.HAUT);
				}
				nbDeplacements++;
			}
        	
        	
        	// On prend l'objet sur la case
        	prendre();
        	
        	
        	
        	
    	}
    	
    	
    	
    	farine = ((Pierre)stockPierre[0]).utiliser(stockBle);
    	
    	fairePain();
    	
    	
    	/*
    	 * Aller en 9x9
    	 * */
    	
    	
    	// On défini donc ou on va
    	int caseDestinationX = 9;
    	int caseDestinationY = 9;
    	
    	
    	// On se déplace jusqu'à caseDestination en X puis Y
    	// On déplace les X
    	while(caseCourante.getPositionX() != caseDestinationX) {
    		if(caseCourante.getPositionX() > caseDestinationX) {
    			seDeplacer(Direction.GAUCHE);
    		}
    		else {
    			seDeplacer(Direction.DROITE);
    		}
    		nbDeplacements++;
    	}
    	
    	// On déplace Y
		while(caseCourante.getPositionY() != caseDestinationY) {
			if(caseCourante.getPositionY() > caseDestinationY) {
				seDeplacer(Direction.BAS);
			}
			else {
				seDeplacer(Direction.HAUT);
			}
			nbDeplacements++;
		}
		
		if(caseCourante.getPositionX() == 9 && caseCourante.getPositionY() == 9) {
			return nbDeplacements;
		}
		else {
			return 0;
		}
    	
    }
    
    
    
	
    
}






