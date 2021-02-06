package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//OneMaxAlgo(int taillePopulation, int n, int nbGenerations, String selection, String croisement, String mutation, String insertion,
		//double tauxCroisement, double tauxMutation){
		
		long start = System.currentTimeMillis();;
		

		OneMaxAlgo onemax = new OneMaxAlgo(20, 100, 2000, "BEST", "UNIFORME", "BITFLIP", "", 0.5, 0.5);
		onemax.run();
		
	    long end = System.currentTimeMillis();;
	    System.out.println("time : "+(end - start) + " ms");
	}

}
