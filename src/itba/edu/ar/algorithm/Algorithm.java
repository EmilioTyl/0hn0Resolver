package itba.edu.ar.algorithm;

import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSStatistics;
import itba.edu.ar.gps.impl.OriginalStatistics;

public abstract class Algorithm {

	public abstract Cost getCost();
	
	public boolean shouldShuffleRules(){
		return false;
	}

	public void execute(GPSProblem problem) {
		execute(problem, new OriginalStatistics());
	}


	public abstract void execute(GPSProblem problem, GPSStatistics statistics);
	
}
