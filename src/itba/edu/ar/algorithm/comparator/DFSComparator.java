package itba.edu.ar.algorithm.comparator;

import java.util.Comparator;

import itba.edu.ar.gps.GPSNode;

public class DFSComparator implements Comparator<GPSNode>{

	@Override
	public int compare(GPSNode o1, GPSNode o2) {
		return o2.getDepth()-o1.getDepth();
	}
	
}
