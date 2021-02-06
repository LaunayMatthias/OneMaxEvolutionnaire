package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import CroisementPackage.Croisement;
import CroisementPackage.CroisementFactory;
import MutationPackage.Mutation;
import MutationPackage.MutationFactory;
import SelectionPackage.Selection;
import SelectionPackage.SelectionFactory;

public class OneMaxAlgo {
	
	private CroisementFactory croisementFactory = new CroisementFactory();
	private Croisement typeCroisement = null;
	
	private MutationFactory mutationFactory = new MutationFactory();
	private Mutation typeMutation = null;
	
	private SelectionFactory selectionFactory = new SelectionFactory();
	private Selection typeSelection = null;
	
//	private InsertionFactory insertionFactory = new InsertionFactory();
//	private Insertion typeInsertion = null;
	
	private int popSize;

	private ArrayList<int[]> population;
	
	private int n;
	
	private int maxGen;
	
	private boolean isDone = false;
	
	private double tauxMutation, tauxCroisement;
	
	FileWriter sortie = null;
	
	OneMaxAlgo(int taillePopulation, int n, int nbGenerations, String selection, String croisement, String mutation, String insertion,
			double tauxCroisement, double tauxMutation){
		
		this.n = n;
		this.popSize = taillePopulation;
		this.maxGen = nbGenerations;
		this.tauxCroisement = tauxCroisement;
		this.tauxMutation = tauxMutation;
		
		typeCroisement = croisementFactory.getCroisement(croisement);
		typeMutation = mutationFactory.getMutation(mutation);
		typeSelection = selectionFactory.getSelection(selection);
//		typeInsertion = insertionFactory.getInsertion(insertion);
		
		this.population = new ArrayList<int[]>();

		// initialisation
		int[] initValue = new int[this.n];
		Arrays.fill(initValue, 0);
		for (int i = 0; i < this.popSize; i++) {
			this.population.add(initValue);
		}
		
		//fichier de sortie
		try {
			sortie = new FileWriter("sortie.txt");
			sortie.write("0 0 0\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void run() throws IOException {
		int iteration = 0;
		
		while(!this.isDone && iteration != this.maxGen) {
			
			//selection
			System.out.println("parents selectionnes :");
			ArrayList<int[]> parents = typeSelection.selectionner(population);
			int[] parent1 = parents.get(0);
			int[] parent2 = parents.get(1);
			afficheIndividu(parent1);
			afficheIndividu(parent2);
			System.out.println("=================================================");

			//croisement
			//AJOUTER TAUX DE CROISEMENT 
			ArrayList<int[]> children = typeCroisement.croiser(parent1, parent2);
			int[] child1 = children.get(0);
			int[] child2 = children.get(1);
			System.out.println("croisement :");
			afficheIndividu(child1);
			afficheIndividu(child2);
			System.out.println("=================================================");

			// mutation
			//AJOUTER TAUX DE MUTATION 
			child1 = typeMutation.muter(child1);
			child2 = typeMutation.muter(child2);
			System.out.println("mutation :");
			afficheIndividu(child1);
			afficheIndividu(child2);
			System.out.println("=================================================");
			
			//insertion
			this.population.remove(0); //on retire les 2 pires individus
			this.population.remove(0);
			this.population.add(child1);
			this.population.add(child2);

			//tri dans l'ordre croissant de fitness 
			Collections.sort(population, new Comparator<int[]>() {
			    public int compare(int[] o1, int[] o2) {
			        return Arrays.stream(o1).sum() - Arrays.stream(o2).sum();
			    }
			});
			
			int bestIndividualFitness = bestIndividualFitnessEvaluation(population);
			int populationFitness = globalFitnessEvaluation(population);
			System.out.println("BEST FITTED :" + bestIndividualFitness);
			System.out.println("GLOBAL FITNESS : " + populationFitness);
			
			iteration++;
			
			//ecriture dans le fichier de sortie
			sortie.write(iteration + " " +bestIndividualFitness+ " "+ populationFitness+ "\n");
			
			
		}
		if(this.maxGen == iteration) {
			System.out.println("nombre max de generations atteint");
		}
		sortie.close();
		affichePop();
		System.out.println("nbIt = "+iteration);
	}
	
//	public int[] getBestFitted(ArrayList<int[]> population) {
////		int maxFit = 0;
////		int[] bestFitted = this.population.get(0); //initialized as worst fitted individual
////		for(int[] individual : population) {
////			int individualFitness= Arrays.stream(individual).sum();
////			if(individualFitness > maxFit) {
////				maxFit = individualFitness;
////				bestFitted = individual;
////			}
////		}
//		Collections.sort(population, new Comparator<int[]>() {
//		    public int compare(int[] o1, int[] o2) {
//		        return Arrays.stream(o1).sum() - Arrays.stream(o2).sum();
//		    }
//		});
//		return population.get(population.size()-1);
//	}
//	
	
	public int bestIndividualFitnessEvaluation(ArrayList<int[]> population) {
//		int[] bestFitted = getBestFitted(population);
		int[] bestFitted = population.get(population.size()-1); //liste en ordre croissant de fitness -> dernier = best fitted
		int bestFitness = Arrays.stream(bestFitted).sum();
		if(bestFitness == this.n) {
			this.isDone = true;
			return 100;
		} else {
			return (int)(((double)bestFitness / n)*100);
		}
 	}
	
	public int globalFitnessEvaluation(ArrayList<int[]> population) {
		int populationSum = 0;
		for(int[] individual : population) {
			populationSum += ((double)Arrays.stream(individual).sum()/individual.length)*100;
		}
		return populationSum/this.popSize;
	}
		
	public void afficheIndividu(int[] individu) {
		for(int i = 0; i<individu.length; i++) {
			System.out.print(individu[i]);
		}
		System.out.println();
	}
	
	public void affichePop() {
		System.out.println("====================POPULATION===================");
		for(int[] individual : this.population) {
			for(int i = 0; i<individual.length; i++) {
				System.out.print(individual[i]);
			}
			System.out.print(" ");
		}
		System.out.println("\n=================================================");
	}

}
