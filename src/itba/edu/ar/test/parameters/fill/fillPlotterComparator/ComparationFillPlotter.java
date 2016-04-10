package itba.edu.ar.test.parameters.fill.fillPlotterComparator;

import java.io.IOException;
import java.util.List;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.test.plotter.Plotter;

public interface ComparationFillPlotter {
	public void fill(Algorithm a1, Heuristic h1, Algorithm a2, Heuristic h2, List<String> exceptions2,
			int simulationTimes, int[] boardDimensions, Plotter plotter) throws IOException;

	public String getStat();
}
