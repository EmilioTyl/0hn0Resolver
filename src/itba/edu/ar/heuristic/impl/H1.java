package itba.edu.ar.heuristic.impl;

import java.awt.Point;

import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.model.OhnO;

public class 
H1 extends Heuristic{

	@Override
	public int getHeuristicValue(OhnO state) {
		int ans = 0;
		for (Point numberPosition : state.getNumbersPositions()) {
			int weight = state.weight(numberPosition);
			if (weight < 0)
				return Integer.MAX_VALUE;
			ans += weight;
		}
		return ans;
	}
	
	
}
