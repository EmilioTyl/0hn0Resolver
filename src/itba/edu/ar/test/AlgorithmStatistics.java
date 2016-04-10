package itba.edu.ar.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.impl.AStar;
import itba.edu.ar.algorithm.impl.Greedy;
import itba.edu.ar.gps.api.GPSStatistics;
import itba.edu.ar.gps.impl.BasicStatistics;
import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.heuristic.impl.H1;
import itba.edu.ar.heuristic.impl.H2;
import itba.edu.ar.input.FileParser;
import itba.edu.ar.resolver.OhnO.OhnOProblem;
import itba.edu.ar.test.plotter.Plotter;
import itba.edu.ar.test.plotter.SeriesPlotter;

public class AlgorithmStatistics {

	private static int maxQuantityofBoardsPerDimension = 4;
	private static String filePath = System.getProperty("user.dir") + "/doc/";

	private interface Stat {
		public double getValue(GPSStatistics statistics);
	}

	private static Stat executionTimeGetter = (statistics) -> statistics.getExecutionTime();
	private static Stat explodedNodesGetter = (statistics) -> statistics.getExplodedNodes();

	public static void explodedNodes(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes,
			int[] boardDimensions, Plotter plotter) throws IOException {
		addStatToPlotter(a, h, exceptions, simulationTimes, boardDimensions, plotter, explodedNodesGetter);
	}

	public static void executionTime(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes,
			int[] boardDimensions, Plotter plotter) throws IOException {
		addStatToPlotter(a, h, exceptions, simulationTimes, boardDimensions, plotter, executionTimeGetter);
	}

	public static void compareExplodedNodes(Algorithm a1, Heuristic h1, List<String> exceptions1, Algorithm a2,
			Heuristic h2, List<String> exceptions2, int simulationTimes, int[] boardDimensions, Plotter plotter)
			throws IOException {
		compare(a1, h1, exceptions1, a2, h2, exceptions2, simulationTimes, boardDimensions, plotter,
				explodedNodesGetter);
	}

	public static void compareExecutionTime(Algorithm a1, Heuristic h1, List<String> exceptions1, Algorithm a2,
			Heuristic h2, List<String> exceptions2, int simulationTimes, int[] boardDimensions, Plotter plotter)
			throws IOException {
		compare(a1, h1, exceptions1, a2, h2, exceptions2, simulationTimes, boardDimensions, plotter,
				executionTimeGetter);
	}

	private static void compare(Algorithm a1, Heuristic h1, List<String> exceptions1, Algorithm a2, Heuristic h2,
			List<String> exceptions2, int simulationTimes, int[] boardDimensions, Plotter plotter, Stat stat)
			throws IOException {

		for (int dimension : boardDimensions) {
			for (int file = 1; file <= maxQuantityofBoardsPerDimension; file++) {
				String fileName = dimension + "x" + dimension + "_" + file;

				double executionTime1 = getStat(a1, h1, simulationTimes, fileName, exceptions1, stat);
				double executionTime2 = getStat(a2, h2, simulationTimes, fileName, exceptions2, stat);

				plotter.addSeriesPoint(dimension, executionTime1, executionTime2);
			}
		}

	}

	private static void addStatToPlotter(Algorithm a, Heuristic h, List<String> exceptions, int simulationTimes,
			int[] boardDimensions, Plotter plotter, Stat stat) throws IOException {
		for (int dimension : boardDimensions) {
			for (int file = 1; file <= maxQuantityofBoardsPerDimension; file++) {
				String fileName = dimension + "x" + dimension + "_" + file;

				double executionTime = getStat(a, h, simulationTimes, fileName, exceptions, stat);

				plotter.addSeriesPoint(dimension, executionTime);

			}
		}
	}

	private static double getStat(Algorithm a, Heuristic h, int simulationTimes, String fileName,
			List<String> exception, Stat stat) throws IOException {
		if (exception != null && exception.contains(fileName))
			return 0;

		String absFilePath = filePath + fileName;
		return getStat(a, h, simulationTimes, absFilePath, stat);
	}

	private static double getStat(Algorithm a, Heuristic h, int simulationTimes, String filePath, Stat stat)
			throws IOException {
		FileParser fp = new FileParser();
		fp.parseFile(filePath);
		OhnOProblem problem;
		double avgExecutionTime = 0;
		boolean outOfMemoryA = false;

		for (int i = 0; i < simulationTimes; i++) {
			try {
				if (!outOfMemoryA) {
					problem = execute(a, h, fp, new BasicStatistics());
					GPSStatistics cs = problem.getCompoundStatistics();
					avgExecutionTime += stat.getValue(cs);
				}
			} catch (OutOfMemoryError e) {
				outOfMemoryA = true;
				avgExecutionTime += Integer.MAX_VALUE;
			}
		}

		return avgExecutionTime / simulationTimes;
	}

	private static OhnOProblem execute(Algorithm algorithm, Heuristic heuristic, FileParser fp,
			GPSStatistics statistics) {
		OhnOProblem problem = getProblem(algorithm, heuristic, fp, statistics);
		algorithm.execute(problem, statistics);
		return problem;
	}

	private static OhnOProblem getProblem(Algorithm algorithm, Heuristic heuristic, FileParser fp,
			GPSStatistics statistics) {
		return new OhnOProblem(algorithm, heuristic, fp.getBoardX(), fp.getBoardY(), fp.getTokens(), statistics);
	}

}
