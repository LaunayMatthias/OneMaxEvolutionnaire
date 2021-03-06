package MutationPackage;

import java.util.Random;

public class OneFlipMutation implements Mutation {

	@Override
	public int[] muter(int[] individual) {
		Random ran = new Random(); 
		int randomBit = ran.nextInt(individual.length);
		individual[randomBit] = individual[randomBit] == 0 ? 1 : 0;
		return individual;
	}

	@Override
	public String toString() {
		return getType().toString();
	}

	@Override
	public MutationType getType() {
		return MutationType.ONEFLIP;
	}
	
	@Override
	public int getNumber() {
		return 1;
	}
}
