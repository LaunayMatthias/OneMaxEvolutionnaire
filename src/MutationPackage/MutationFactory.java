package MutationPackage;


public class MutationFactory {

	public Mutation getMutation(String mutation) {
		if(mutation == null) {
			return null;
		} 
		if(mutation.equalsIgnoreCase("BITFLIP")){
			return new BitFlipMutation();
		} else if(mutation.equalsIgnoreCase("1FLIP")) {
			return new OneFlipMutation();
		} else if(mutation.equalsIgnoreCase("3FLIP")) {
			return new ThreeFlipMutation();
		} else if(mutation.equalsIgnoreCase("5FLIP")) {
			return new FiveFlipMutation();
		}
		return null;
	}
}
