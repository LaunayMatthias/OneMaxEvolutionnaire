package MutationPackage;

public interface Mutation {
	int[] muter(int[] individual);
	MutationType getType();
	int getNumber();
}
