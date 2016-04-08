package itba.edu.ar.algorithm.impl;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.comparator.GreedyComparator;
import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.algorithm.cost.NoCost;
import itba.edu.ar.gps.GPSEngine;
import itba.edu.ar.gps.api.GPSProblem;

public class Greedy implements Algorithm{

	@Override
	public Cost getCost() {
		return new NoCost();
	}

	@Override
	public void execute(GPSProblem problem) {
		GPSEngine engine = new GPSEngine(new GreedyComparator(problem), problem);
		engine.engine();
	}
	
	@Override
	public String toString() {
		return "Greedy";
	}

}
