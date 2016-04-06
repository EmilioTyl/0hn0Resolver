package itba.edu.ar.algorithm;

import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.api.GPSProblem;

public interface Algorithm {

	public Cost getCost();
	
	public void execute(GPSProblem problem);
	
}
