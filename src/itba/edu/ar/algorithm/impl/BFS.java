package itba.edu.ar.algorithm.impl;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.comparator.BFSComparator;
import itba.edu.ar.algorithm.cost.ConstantCost;
import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.GPSEngine;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSStatistics;

public class BFS extends Algorithm{

	@Override
	public Cost getCost() {
		return new ConstantCost();
	}


	@Override
	public String toString() {
		return "BFS";
	}

	@Override
	public void execute(GPSProblem problem, GPSStatistics statistics) {
		GPSEngine engine = new GPSEngine(new BFSComparator(), problem,statistics);
		engine.engine();
		
	}
	
}
