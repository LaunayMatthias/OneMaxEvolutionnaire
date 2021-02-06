package SelectionPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BestTwoSelection implements Selection {

	@Override
	public ArrayList<int[]> selectionner(ArrayList<int[]> population) {
		ArrayList<int[]> bestTwo = new ArrayList<int[]>();
		Collections.sort(population, new Comparator<int[]>() {
		    public int compare(int[] o1, int[] o2) {
		        return Arrays.stream(o1).sum() - Arrays.stream(o2).sum();
		    }
		});
		bestTwo.add(population.get(population.size()-1));
		bestTwo.add(population.get(population.size()-2));
		return bestTwo;
	}

}
