package itba.edu.ar.gps.api;

import itba.edu.ar.gps.GPSNode;

public interface GPSStatistics {

	public void startSearch();

	public void endSearch();

	public void analizeNode();

	public void explodeNode();

	public void goalNode(GPSNode currentNode);

	public void solutionNotFound();

	public void printStatistics();

	long getSolutionDepth();

	long getSolutionCost();

	long getExplodedNodes();

	long getAnalizedNodes();

	double getExecutionTime();

	
}
