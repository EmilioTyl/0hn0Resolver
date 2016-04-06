package itba.edu.ar.algorithm.comparator;

import java.util.Comparator;

import itba.edu.ar.gps.GPSNode;
import itba.edu.ar.gps.api.GPSProblem;

public class AStarComparator implements Comparator<GPSNode> {

	private GPSProblem problem;

	public AStarComparator(GPSProblem problem) {
		super();
		this.problem = problem;
	}

	@Override
	public int compare(GPSNode o1, GPSNode o2) {
		return f(o1) - f(o2);
	}

	private int f(GPSNode node) {
		return node.getCost() + problem.getHValue(node.getState());
	}

}
