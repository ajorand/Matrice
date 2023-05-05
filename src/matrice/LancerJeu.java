package matrice;



public class LancerJeu {
	
	public static void main(String[] arg) {	
		
		int nbrDeplacement;
		
		
		// Matrice 1
		Ressource[][] matrix1 = new Ressource[10][10];
	    for (int i = 0; i < 10; i++) {
	    	matrix1 [i][i] = new Ble("ble" + i);
	    }
	    matrix1 [0][1] = new Pierre("Paul");
	    matrix1 [2][5] = new Bois("bois25");
	    matrix1 [3][1] = new Bois("bois31");
	    matrix1 [4][8] = new Bois("bois48");
	    matrix1 [5][2] = new Bois("bois52");
	    matrix1 [5][3] = new Bois("bois53");
	    matrix1 [9][1] = new Bois("bois91");
	    
		
		/*
		// Matrice 2
		Ressource[][] matrix2 = new Ressource[10][10];
		for (int i = 0; i < 10; i++){
			if (i % 2 == 0){
				matrix2 [i][3] = new Ble("ble" + i);
			} 
			else{
				matrix2 [i][4] = new Ble("ble" + i);
			}
		}
		matrix2 [8][5] = new Pierre("Jacques");
		matrix2 [0][5] = new Bois("bois05");
		matrix2 [5][1] = new Bois("bois51");
		matrix2 [6][8] = new Bois("bois68");
		matrix2 [8][2] = new Bois("bois82");
		matrix2 [8][3] = new Bois("bois83");
		matrix2 [9][9] = new Bois("bois99");
		matrix2 [9][9] = new Ble("tt");
		*/
		
		
		/*
		// Matrice 3
		Ressource[][] matrix3 = new Ressource[10][10];
		for (int i = 0; i < 10; i++)
		{
		if (i % 2 == 0)
		{
		matrix3 [i][3] = new Ble("ble" + i);
		} else
		{
		matrix3 [i][4] = new Ble("ble" + i);
		}
		}
		matrix3 [8][5] = new Pierre("Jacques");
		matrix3 [0][0] = new Bois("bois00");
		matrix3 [0][1] = new Bois("bois01");
		matrix3 [0][2] = new Bois("bois02");
		matrix3 [0][3] = new Bois("bois03");
		matrix3 [0][4] = new Bois("bois04");
		*/
		
		
	    Hero hero1 = new Hero("toto");
		// System.out.println(hero1.getNom());
		
	    // Changer en fonction de la matrice
	    nbrDeplacement = hero1.jouer(matrix1);
	    
	    System.out.println("Gagné en " + nbrDeplacement + " coups !");
		
	    
	}
}
