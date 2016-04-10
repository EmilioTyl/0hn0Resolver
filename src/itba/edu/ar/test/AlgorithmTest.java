package itba.edu.ar.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.impl.AStar;
import itba.edu.ar.algorithm.impl.Greedy;
import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.heuristic.impl.H1;
import itba.edu.ar.heuristic.impl.H2;
import itba.edu.ar.test.plotter.Plotter;
import itba.edu.ar.test.plotter.SeriesPlotter;

public class AlgorithmTest {

	private static String path = System.getProperty("user.dir") + "/";
	private static List<String> H1_EXCEPTIONS;
	private static int[] BOARD_DIMENSIONS = { 4, 5};//, 6, 7, 8, 9 };
	private static int[] H1_BOARD_DIMENSIONS = { 4, 5, 6, 7 };
	private static int simulationTimes = 10;

	static {
		String[] h1Exceptions = { "5x5_1", "6x6_4", "7x7_2", "7x7_4" };
		H1_EXCEPTIONS = Arrays.asList(h1Exceptions);
	}

	public static void main(String[] args) throws IOException {
//		greedyVsAStarComparation(compareExecutionTime);
//		greedyVsAStarComparation(compareExplodedNodes);
		aStar(executionTime);
//		aStar(explodedNodes);
	}

	private static void greedyVsAStarComparation(ComparationFillPlotter fp) throws IOException {
		Algorithm a1 = new Greedy();
		Algorithm a2 = new AStar();
		List<String> seriesNames = new ArrayList<String>();
		seriesNames.add(a1.toString() + " with H1");
		seriesNames.add(a2.toString() + " with H2");

		Plotter plotter = new SeriesPlotter(a1.toString() + " Vs " + a2.toString() + " - " + fp.getStat(),
				"Board Size (NxN)",fp.getStat(), seriesNames, path);
		fp.fill(a1, new H1(), H1_EXCEPTIONS, a2, new H2(), H1_EXCEPTIONS, simulationTimes, H1_BOARD_DIMENSIONS,
				plotter);
		plotter.plot();
	}

	private static void aStar(FillPlotter fp) throws IOException {
		Algorithm a1 = new AStar();
		List<String> seriesNames = new ArrayList<String>();
		seriesNames.add(a1.toString() + " with H2");

		Plotter plotter = new SeriesPlotter(a1.toString()+" "+fp.getStat(), "Board Size (NxN)", fp.getStat(), seriesNames,
				path);
		fp.fill(a1, new H2(), null, simulationTimes, BOARD_DIMENSIONS, plotter);
		plotter.plot();
	}

	private interface ComparationFillPlotter {
		public void fill(Algorithm a1, Heuristic h1, List<String> exceptions1, Algorithm a2, Heuristic h2,
				List<String> exceptions2, int simulationTimes, int[] boardDimensions, Plotter plotter)
				throws IOException;

		public String getStat();
	}

	private static ComparationFillPlotter compareExecutionTime = new ComparationFillPlotter() {

		@Override
		public String getStat() {
			return "Execution Time (milliseconds)";
		}

		@Override
		public void fill(Algorithm a1, Heuristic h1, List<String> exceptions1, Algorithm a2, Heuristic h2,
				List<String> exceptions2, int simulationTimes, int[] boardDimensions, Plotter plotter)
				throws IOException {
			AlgorithmStatistics.compareExecutionTime(a1, h1, exceptions1, a2, h2, exceptions2, simulationTimes,
					boardDimensions, plotter);
		}
	};

	private static ComparationFillPlotter compareExplodedNodes = new ComparationFillPlotter() {

		@Override
		public String getStat() {
			return "Exploded Nodes";
		}

		@Override
		public void fill(Algorithm a1, Heuristic h1, List<String> exceptions1, Algorithm a2, Heuristic h2,
				List<String> exceptions2, int simulationTimes, int[] boardDimensions, Plotter plotter)
				throws IOException {
			AlgorithmStatistics.compareExplodedNodes(a1, h1, exceptions1, a2, h2, exceptions2, simulationTimes,
					boardDimensions, plotter);
		}
	};

	private interface FillPlotter {
		public void fill(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes, int[] boardDimensions,
				Plotter plotter) throws IOException;
		
		public String getStat();
	}

	private static FillPlotter executionTime = new FillPlotter() {
		
		@Override
		public void fill(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes, int[] boardDimensions,
				Plotter plotter) throws IOException {
			AlgorithmStatistics.executionTime(a, h, exceptions, simulationTimes, boardDimensions, plotter);

		}

		@Override
		public String getStat() {
			return "Execution Time";
		}
	};
	
	private static FillPlotter explodedNodes = new FillPlotter() {
		
		@Override
		public void fill(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes, int[] boardDimensions,
				Plotter plotter) throws IOException {
			AlgorithmStatistics.explodedNodes(a, h, exceptions, simulationTimes, boardDimensions, plotter);

		}

		@Override
		public String getStat() {
			return "Exploded Nodes";
		}
	};
	
}
