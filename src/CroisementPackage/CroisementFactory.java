package CroisementPackage;

public class CroisementFactory {
	
	public Croisement getCroisement(String croisement) {
		if(croisement == null) {
			return null;
		} 
		if(croisement.equalsIgnoreCase("UNIFORME")){
			return new CroisementUniforme();
		} else if(croisement.equalsIgnoreCase("MONOPOINT")){
			return new CroisementMonopoint();
		}
		return null;
	}

}
