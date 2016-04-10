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
import itba.edu.ar.heuristic.impl.H2Admissible;
import itba.edu.ar.heuristic.impl.H2Inadmissible;
import itba.edu.ar.test.parameters.AlgorithmAndHeuristic;
import itba.edu.ar.test.parameters.fill.fillPlotter.FillPlotter;
import itba.edu.ar.test.parameters.fill.fillPlotterComparator.ComparationFillPlotter;
import itba.edu.ar.test.plotter.Plotter;
import itba.edu.ar.test.plotter.SeriesPlotter;

public class AlgorithmTest {

	private static String path = System.getProperty("user.dir") + "/";
	private static List<String> H1_EXCEPTIONS;
	private static int[] BOARD_DIMENSIONS = { 4, 5, 6, 7, 8, 9 };
	private static int[] H1_BOARD_DIMENSIONS = { 4, 5, 6, 7 };
	private static int[] ADMISSIBLE_H2_BOARD_DIMENSIONS = { 4, 5, 6 };
	private static List<String> INADMISSIBLE_H2_EXCEPTIONS;
	private static int simulationTimes = 10;
	private static List<String> ADMISSIBLE_H2_EXCEPTIONS;

	private static AlgorithmAndHeuristic aStarWithH2Inadmissible;
	private static AlgorithmAndHeuristic aStarWithH2Admissible;
	private static AlgorithmAndHeuristic greedyWithH1;

	static {
		String[] h1Exceptions = { "5x5_1", "6x6_4", "7x7_2", "7x7_4" };
		H1_EXCEPTIONS = Arrays.asList(h1Exceptions);
		String[] inadmissibleH2Exceptions = { "8x8_4" };
		INADMISSIBLE_H2_EXCEPTIONS = Arrays.asList(inadmissibleH2Exceptions);
		String[] admissibleH2Exceptions = { "5x5_1", "6x6_2","6x6_3", "6x6_4" };
		ADMISSIBLE_H2_EXCEPTIONS = Arrays.asList(admissibleH2Exceptions);

		aStarWithH2Inadmissible = new AlgorithmAndHeuristic(new AStar(), new H2Inadmissible(),
				INADMISSIBLE_H2_EXCEPTIONS);
		aStarWithH2Admissible = new AlgorithmAndHeuristic(new AStar(), new H2Admissible(), ADMISSIBLE_H2_EXCEPTIONS);
		greedyWithH1 = new AlgorithmAndHeuristic(new Greedy(), new H1(), H1_EXCEPTIONS);

	}

	public static void main(String[] args) throws IOException {
		//getStatComparation(compareExecutionTime, greedyWithH1, aStarWithH2Inadmissible, H1_BOARD_DIMENSIONS);
		//getStatComparation(compareExplodedNodes, greedyWithH1, aStarWithH2Inadmissible, H1_BOARD_DIMENSIONS);
		//getStat(executionTime, aStarWithH2Inadmissible, BOARD_DIMENSIONS);
		//getStat(explodedNodes, aStarWithH2Inadmissible, BOARD_DIMENSIONS);
	
		getStatComparation(compareExecutionTime, greedyWithH1, aStarWithH2Admissible, ADMISSIBLE_H2_BOARD_DIMENSIONS);
		//getStatComparation(compareExplodedNodes, greedyWithH1, aStarWithH2Admissible, ADMISSIBLE_H2_BOARD_DIMENSIONS);	
		//getStat(executionTime, aStarWithH2Admissible, ADMISSIBLE_H2_BOARD_DIMENSIONS);
		//getStat(explodedNodes, aStarWithH2Admissible, ADMISSIBLE_H2_BOARD_DIMENSIONS);
	}

	private static void getStatComparation(ComparationFillPlotter fp, AlgorithmAndHeuristic aah1,
			AlgorithmAndHeuristic aah2, int[] boardDimensions) throws IOException {
		List<String> seriesNames = new ArrayList<String>();
		seriesNames.add(aah1.toString());
		seriesNames.add(aah2.toString());

		Plotter plotter = new SeriesPlotter(aah1 + " Vs " + aah2 + " - " + fp.getStat(), "Board Size (NxN)",
				fp.getStat(), seriesNames, path);
		fp.fill(aah1.getAlgorithm(), aah1.getHeuristic(), aah2.getAlgorithm(), aah2.getHeuristic(),
				getExceptionsUnion(aah1.getExceptions(), aah2.getExceptions()), simulationTimes, boardDimensions,
				plotter);
		plotter.plot();
	}

	private static List<String> getExceptionsUnion(List<String> exceptions, List<String> exceptions2) {
		List<String> ans = new ArrayList<String>();
		ans.addAll(exceptions);
		ans.addAll(exceptions2);
		return ans;
	}

	private static void getStat(FillPlotter fp, AlgorithmAndHeuristic aah, int[] boardDimensions) throws IOException {
		List<String> seriesNames = new ArrayList<String>();
		seriesNames.add(aah.toString());

		Plotter plotter = new SeriesPlotter(aah + " " + fp.getStat(), "Board Size (NxN)", fp.getStat(), seriesNames,
				path);
		fp.fill(aah.getAlgorithm(), aah.getHeuristic(), aah.getExceptions(), simulationTimes, boardDimensions, plotter);
		plotter.plot();
	}

	private static ComparationFillPlotter compareExecutionTime = new ComparationFillPlotter() {

		@Override
		public String getStat() {
			return "Execution Time (Milliseconds)";
		}

		@Override
		public void fill(Algorithm a1, Heuristic h1, Algorithm a2, Heuristic h2, List<String> exceptions2,
				int simulationTimes, int[] boardDimensions, Plotter plotter) throws IOException {
			AlgorithmStatistics.compareExecutionTime(a1, h1, a2, h2, exceptions2, simulationTimes, boardDimensions,
					plotter);
		}
	};

	private static ComparationFillPlotter compareExplodedNodes = new ComparationFillPlotter() {

		@Override
		public String getStat() {
			return "Exploded Nodes";
		}

		@Override
		public void fill(Algorithm a1, Heuristic h1, Algorithm a2, Heuristic h2, List<String> exceptions,
				int simulationTimes, int[] boardDimensions, Plotter plotter) throws IOException {
			AlgorithmStatistics.compareExplodedNodes(a1, h1, a2, h2, exceptions, simulationTimes, boardDimensions,
					plotter);
		}
	};

	private static FillPlotter executionTime = new FillPlotter() {

		@Override
		public void fill(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes, int[] boardDimensions,
				Plotter plotter) throws IOException {
			AlgorithmStatistics.executionTime(a, h, exceptions, simulationTimes, boardDimensions, plotter);

		}

		@Override
		public String getStat() {
			return "Execution Time (Milliseconds)";
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
