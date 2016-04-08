package itba.edu.ar.gps.impl;

import itba.edu.ar.gps.api.GPSStatistics;

public class CompoundStatistics extends BasicStatistics {

	private GPSStatistics statistics;

	public CompoundStatistics(GPSStatistics statistics,String name) {
		super(name);
		this.statistics = statistics;
	}
	
	public CompoundStatistics(GPSStatistics statistics) {
		super();
		this.statistics = statistics;
	}


	public void printStatistics() {
		System.out.println("Phase 1");
		statistics.printStatistics();
		System.out.println("--------------------------------");

		System.out.println("Phase 2");
		super.printStatistics();
		System.out.println("--------------------------------");
		
		System.out.println("Total statistics");
		super.printStatistics(getAnalizedNodes() + statistics.getAnalizedNodes(),
				getExplodedNodes() + statistics.getExplodedNodes(), getSolutionCost() + statistics.getSolutionCost(),
				getSolutionDepth(), getExecutionTime() + statistics.getExecutionTime());
	}

}
