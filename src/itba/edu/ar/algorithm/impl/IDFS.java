package itba.edu.ar.algorithm.impl;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.comparator.DFSComparator;
import itba.edu.ar.algorithm.cost.ConstantCost;
import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.GPSEngine;
import itba.edu.ar.gps.api.GPSProblem;

public class IDFS implements Algorithm{

	@Override
	public Cost getCost() {
		return new ConstantCost();
	}

	@Override
	public void execute(GPSProblem problem) {
		boolean finished = false;
		int iteration = 1;
		while (!finished) {
			GPSEngine engine = new GPSEngine(new DFSComparator(), problem, iteration);
			finished = engine.engine();
			System.out.println("vuelta " + iteration);
			iteration++;
		}		
	}
	
	@Override
	public String toString() {
		return "IDFS";
	}

}
