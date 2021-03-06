package MutationPackage;

import java.util.Random;
import java.util.stream.IntStream;

public class ThreeFlipMutation implements Mutation {

	@Override
	public int[] muter(int[] individual) {
		Random r = new Random(); 
		IntStream s = r.ints(0, individual.length).distinct().limit(3);
		s.forEach(randomBit -> {
			individual[randomBit] = individual[randomBit] == 0 ? 1 : 0;
		});
	return individual;
	}
	
	@Override
	public String toString() {
		return getType().toString();
	}
	
	@Override
	public MutationType getType() {
		return MutationType.THREEFLIP;
	}
	
	@Override
	public int getNumber() {
		return 2;
	}
}
