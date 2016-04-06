package itba.edu.ar.algorithm.comparator;

import java.util.Comparator;

import itba.edu.ar.gps.GPSNode;

public class DFSComparator implements Comparator<GPSNode>{

	//Always o1 > o2
	@Override
	public int compare(GPSNode o1, GPSNode o2) {
		return 1;
	}
	
}
