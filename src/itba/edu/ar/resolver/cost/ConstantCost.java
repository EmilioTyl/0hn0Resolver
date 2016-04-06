package itba.edu.ar.resolver.cost;

public class ConstantCost implements Cost{

	@Override
	public int getCost() {
		return 1;
	}

}
