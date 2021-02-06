package SelectionPackage;

public class SelectionFactory {

	public Selection getSelection(String selection) {
		if(selection == null) {
			return null;
		} 
		if(selection.equalsIgnoreCase("RANDOM")){
			return new RandomSelection();
		} else if(selection.equalsIgnoreCase("TOURNAMENT")){
			return new TournamentSelection();
		} else if(selection.equalsIgnoreCase("BEST")){
			return new BestTwoSelection();
		}
		return null;
	}
}
