package itba.edu.ar.algorithm.impl;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.comparator.DFSComparator;
import itba.edu.ar.algorithm.cost.ConstantCost;
import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.GPSEngine;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSStatistics;

public class DFS extends Algorithm{

	@Override
	public Cost getCost() {
		return new ConstantCost();
	}

	@Override
	public void execute(GPSProblem problem,GPSStatistics statistics) {
		GPSEngine engine = new GPSEngine(new DFSComparator(), problem,statistics);
		engine.engine();
		
	}

	@Override
	public String toString() {
		return "DFS";
	}	
	
}
