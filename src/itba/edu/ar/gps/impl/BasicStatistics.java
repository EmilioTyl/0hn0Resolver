package itba.edu.ar.gps.impl;

import itba.edu.ar.gps.GPSNode;
import itba.edu.ar.gps.api.GPSStatistics;

public class BasicStatistics implements GPSStatistics {

	private static final int TO_MILLISECONDS = 1000;
	private long startTime;
	private double executionTime;
	private long analizedNodes = 0;
	private long explodedNodes = 0;
	private long solutionCost;
	private long solutionDepth;
	private String name;

	public BasicStatistics(String name) {
		this.name=name;
	}
	
	public BasicStatistics() {
		this.name="";
	}
	
	@Override
	public void startSearch() {
		System.out.println(name);
		startTime = System.nanoTime();
	}

	@Override
	public void endSearch() {
		executionTime = (System.nanoTime() - startTime) * TO_MILLISECONDS;
	}

	@Override
	public void analizeNode() {
		analizedNodes++;
	}

	@Override
	public void explodeNode() {
		explodedNodes++;
	}

	@Override
	public void goalNode(GPSNode currentNode) {
		solutionCost = currentNode.getCost();
		solutionDepth = currentNode.getDepth();
	}

	@Override
	public void solutionNotFound() {
		System.err.println("FAILED! solution not found!");
	}

	public double getExecutionTime() {
		return executionTime;
	}

	public long getAnalizedNodes() {
		return analizedNodes;
	}

	public long getExplodedNodes() {
		return explodedNodes;
	}

	public long getSolutionCost() {
		return solutionCost;
	}

	public long getSolutionDepth() {
		return solutionDepth;
	}

	public void printStatistics() {
		printStatistics(analizedNodes, explodedNodes, solutionCost, solutionDepth,
				executionTime);
	}

	protected void printStatistics(long analizedNodes, long explodedNodes, long solutionCost, long solutionDepth,
			double executionTime) {
		System.out.println("Analized states:\t" + analizedNodes);
		System.out.println("Expanded nodes:\t" + explodedNodes);
		System.out.println("Solution cost:\t" + solutionCost);
		System.out.println("Solution depth:\t" + solutionDepth);
		System.out.println("Execution time (milliseconds):\t" + executionTime + "ms");
		System.out.println("OK! solution found!");
	}

}
