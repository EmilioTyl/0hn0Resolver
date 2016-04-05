package itba.edu.ar.resolver.algorithm;

import java.util.Comparator;

import itba.edu.ar.gps.GPSNode;
import itba.edu.ar.gps.api.GPSProblem;

public class Greedy implements Comparator<GPSNode> {

	private GPSProblem problem;
	
	public Greedy(GPSProblem problem) {
		this.problem=problem;
	}
	
	@Override
	public int compare(GPSNode o1, GPSNode o2) {
		return problem.getHValue(o1.getState()) - problem.getHValue(o2.getState());
	}

}
