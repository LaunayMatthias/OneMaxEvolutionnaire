package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//OneMaxAlgo(int taillePopulation, int n, int nbGenerations, String selection, String croisement, String mutation, String insertion,
		//double tauxCroisement, double tauxMutation){
		
		long start = System.currentTimeMillis();;
		

		for(int i=0; i<20; i++) {
			OneMaxAlgo onemax = new OneMaxAlgo(20, 100, 2000, "RANDOM", "UNIFORME", "BITFLIP", "", 0.5, 0.5, true, i);
			onemax.run();
		}
		
		
	    long end = System.currentTimeMillis();;
	    System.out.println("time : "+(end - start) + " ms");
	}

}
