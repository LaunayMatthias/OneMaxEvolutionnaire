package SelectionPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

public class TournamentSelection implements Selection {

	@Override
	public ArrayList<int[]> selectionner(ArrayList<int[]> population) {
		ArrayList<int[]> bestTwo = new ArrayList<int[]>();
		ArrayList<int[]> tournament = new ArrayList<int[]>();
		Random r = new Random(); 
		IntStream s = r.ints(0, population.size()).distinct().limit(5);
		s.forEach(randomNbr -> {
			tournament.add(population.get(randomNbr));
		});
		Collections.sort(tournament, new Comparator<int[]>() {
		    public int compare(int[] o1, int[] o2) {
		        return Arrays.stream(o1).sum() - Arrays.stream(o2).sum();
		    }
		});
		bestTwo.add(tournament.get(tournament.size()-1));
		bestTwo.add(tournament.get(tournament.size()-2));
		return bestTwo;
	}

}
