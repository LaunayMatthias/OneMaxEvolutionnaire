package CroisementPackage;

import java.util.ArrayList;
import java.util.Random;

public class CroisementUniforme implements Croisement {

	@Override
	public ArrayList<int[]> croiser(int[] parent1, int[] parent2) {
		int size = parent1.length;
		int[] child1 = new int[size];
		int[] child2 = new int[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			if (rand.nextBoolean()) {
				child1[i] = parent1[i];
				child2[i] = parent2[i];
			} else {
				child1[i] = parent2[i];
				child2[i] = parent1[i];
			}
		}
		ArrayList<int[]> res = new ArrayList<>();
		res.add(child1);
		res.add(child2);
		return res;
	}

}
