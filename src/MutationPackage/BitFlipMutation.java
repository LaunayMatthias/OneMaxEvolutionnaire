package MutationPackage;

import java.util.Random;

public class BitFlipMutation implements Mutation {

	@Override
	public int[] muter(int[] individual) {
		int size = individual.length;
		Random rand = new Random();
		for(int i=0; i<size; i++) {
			if(rand.nextDouble() <= (double)1/size) {
				individual[i] = individual[i] == 0 ? 1 : 0;
			}
		}
		return individual;
	}
	
	@Override
	public String toString() {
		return getType().toString();
	}

	@Override
	public MutationType getType() {
		return MutationType.BITFLIP;
	}

	@Override
	public int getNumber() {
		return 0;
	}
}
