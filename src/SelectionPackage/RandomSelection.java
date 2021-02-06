package SelectionPackage;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomSelection implements Selection {

	@Override
	public ArrayList<int[]> selectionner(ArrayList<int[]> population) {
		ArrayList<int[]> individus = new ArrayList<>();
		
		Random r = new Random();
		IntStream s = r.ints(0, population.size()).distinct().limit(2);
		s.forEach(randomIndividual -> {
			individus.add(population.get(randomIndividual));
		});
		return individus;
	}

	@Override
	public String toString() {
		return "RANDOM";
	}
}
