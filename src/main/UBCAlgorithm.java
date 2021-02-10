package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import MutationPackage.Mutation;
import MutationPackage.MutationFactory;

public class UBCAlgorithm {
	
	private static final int nbArms = 4;
	private int n; //size of individual
	private Map<Integer, Double> sumRewards; //map typeMutation/recompense
	private Map<Integer, Integer> timesPicked;
	double max_upper_bound;
	int typeSelected;

	
	private double total_reward = 0;
	
	private MutationFactory factory;
	private Mutation mutation;
	
	public UBCAlgorithm(int n) {
		this.n = n;
		sumRewards = new HashMap<Integer, Double>();
		sumRewards.put(0, (double) (1/n));
		sumRewards.put(1, (double) (1/n));
		sumRewards.put(2, (double) (3/n));
		sumRewards.put(3, (double) (5/n));
		
		timesPicked = new HashMap<Integer, Integer>();
		for(int i=0; i<nbArms; i++) {
			timesPicked.put(i, 0);
		}
		factory = new MutationFactory();
	
	}
	
	
	public int run(int mutationNumber, int iteration, ArrayList<int[]> children) {
		double upper_bound;
		max_upper_bound = 0.0;
		
		for(int i=0; i<nbArms; i++) {
			if(timesPicked.get(i) > 0) {
				double average_reward = sumRewards.get(i) / timesPicked.get(i);
				double delta_i = Math.sqrt(2 * Math.log(iteration) / timesPicked.get(i));
				upper_bound = average_reward + delta_i;
			} else {
				upper_bound = Double.POSITIVE_INFINITY;
			}
			if(upper_bound > max_upper_bound) {
				max_upper_bound = upper_bound;
				typeSelected = i;
			}
		}
		timesPicked.put(typeSelected, timesPicked.get(typeSelected) + 1);

		
		//calcul reward
		mutation = factory.getMutation(typeSelected);
		double meanRewardArm = 0.0;
		for(int[] child : children) { //on execute la mutation a part pour calculer reward
			double childFitnessBefore= (double)Arrays.stream(child).sum()/(double)n*100;
			mutation.muter(child);
			double childFitnessAfter= (double)Arrays.stream(child).sum()/(double)n*100;

			meanRewardArm+= childFitnessAfter - childFitnessBefore; //compare fitness avant et apres mutation
		}
		meanRewardArm/=2; //moyenne des 2 reward de mutation
		sumRewards.put(typeSelected, sumRewards.get(typeSelected)+meanRewardArm);
		total_reward += meanRewardArm;
		
		return typeSelected;
	}
	
	public void showResults() {
		System.out.println("Times picked");
		for(Entry<Integer, Integer> e :timesPicked.entrySet()) {
			System.out.println(e.getKey() +" "+ e.getValue());
		}
		System.out.println("Rewards");
		for(Entry<Integer, Double> e :sumRewards.entrySet()) {
			System.out.println(e.getKey() +" "+ e.getValue());
		}
		System.out.println("Total reward");
		System.out.println(total_reward);
	}

}
