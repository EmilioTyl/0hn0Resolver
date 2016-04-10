package itba.edu.ar.test.parameters.fill.fillPlotter;

import java.io.IOException;
import java.util.List;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.test.plotter.Plotter;

public interface FillPlotter {
	public void fill(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes, int[] boardDimensions,
			Plotter plotter) throws IOException;

	public String getStat();
}