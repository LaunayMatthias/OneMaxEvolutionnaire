package MutationPackage;

import java.util.Random;
import java.util.stream.IntStream;

public class FiveFlipMutation implements Mutation {

	@Override
	public int[] muter(int[] individual) {
		Random r = new Random(); 
		IntStream s = r.ints(0, individual.length).distinct().limit(5);
		s.forEach(randomBit -> {
			individual[randomBit] = individual[randomBit] == 0 ? 1 : 0;
		});
	return individual;
	}

}
