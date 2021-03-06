package itba.edu.ar.gps.impl;

import itba.edu.ar.gps.api.GPSStatistics;

public class CompoundStatistics extends BasicStatistics {

	private GPSStatistics statistics;

	public CompoundStatistics(GPSStatistics statistics, String name) {
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
		super.printStatistics(getAnalizedNodes(), getExplodedNodes(), getOpenSize(), getSolutionCost(), getSolutionDepth(),
				getExecutionTime());
	}

	public long getOpenSize() {
		return super.getOpenSize()+statistics.getOpenSize();
	}

	@Override
	public long getAnalizedNodes() {
		return super.getAnalizedNodes() + statistics.getAnalizedNodes();
	}

	@Override
	public long getExplodedNodes() {
		return super.getExplodedNodes() + statistics.getExplodedNodes();
	}

	@Override
	public long getSolutionDepth() {
		return super.getSolutionDepth() + statistics.getSolutionDepth();
	}

	@Override
	public long getSolutionCost() {
		return super.getSolutionCost() + statistics.getSolutionCost();
	}

	@Override
	public double getExecutionTime() {
		return super.getExecutionTime() + statistics.getExecutionTime();
	}

}
