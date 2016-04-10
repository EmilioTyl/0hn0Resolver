package itba.edu.ar.algorithm.impl;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.comparator.DFSComparator;
import itba.edu.ar.algorithm.cost.ConstantCost;
import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.GPSEngine;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSStatistics;

public class IDFS extends Algorithm{

	@Override
	public Cost getCost() {
		return new ConstantCost();
	}

	@Override
	public void execute(GPSProblem problem,GPSStatistics statistics) {
		boolean finished = false;
		int iteration = 1;
		while (!finished) {
			GPSEngine engine = new GPSEngine(new DFSComparator(), problem, statistics,iteration);
			System.out.println("\niteration depth: " + iteration);
			finished = engine.engine();
			iteration++;
		}		
	}
	
	@Override
	public String toString() {
		return "IDFS";
	}

}
