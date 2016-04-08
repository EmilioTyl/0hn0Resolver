package itba.edu.ar.algorithm.impl;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.comparator.AStarComparator;
import itba.edu.ar.algorithm.cost.ConstantCost;
import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.GPSEngine;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.resolver.OhnOProblem;

public class AStar implements Algorithm{

	@Override
	public Cost getCost() {
		return new ConstantCost();
	}

	@Override
	public void execute(GPSProblem problem) {
		GPSEngine engine = new GPSEngine(new AStarComparator(problem), problem);
		engine.engine();
	}

	@Override
	public String toString() {
		return "A*";
	}
}
