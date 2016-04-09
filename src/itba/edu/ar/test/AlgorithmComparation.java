package itba.edu.ar.test;

import java.io.IOException;

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

public class AlgorithmComparation {

	private static int[] boardDimensions = { 4, 5 };// , 6, 7, 8, 9 };
	private static int maxQuantityofBoardsPerDimension = 4;
	private static String filePath = System.getProperty("user.dir") + "/doc/";

	public static void main(String[] args) throws IOException {
		compareTime(new Greedy(), new H1(), new AStar(), new H2(), 10);
	}

	public static void compareTime(Algorithm a1, Heuristic h1, Algorithm a2, Heuristic h2, int simulationTimes)
			throws IOException {

		Plotter plotter = new SeriesPlotter();

		for (int dimension : boardDimensions) {
			for (int file = 1; file <= maxQuantityofBoardsPerDimension; file++) {
				compareTime(a1, h1, a2, h2, filePath + dimension + "x" + dimension + "_" + file, plotter,
						simulationTimes);
			}
		}

		plotter.plot();

	}

	public static void compareTime(Algorithm a1, Heuristic h1, Algorithm a2, Heuristic h2, String filePath,
			Plotter plotter, int simulationTimes) throws IOException {
		FileParser fp = new FileParser();
		fp.parseFile(filePath);
		OhnOProblem problem;
		boolean outOfMemoryA1 = false;
		boolean outOfMemoryA2 = false;
		double executiontime1 = 0;

		for (int i = 0; i < simulationTimes; i++) {
			try {
				if (!outOfMemoryA1) {
					problem = execute(a1, h1, fp, new BasicStatistics());
					GPSStatistics cs1 = problem.getCompoundStatistics();
					executiontime1 = cs1.getExecutionTime();
				}
			} catch (OutOfMemoryError e) {
				outOfMemoryA1 = true;
				executiontime1 = Integer.MAX_VALUE;
			}

			problem = execute(a2, h2, fp, new BasicStatistics());
			GPSStatistics cs2 = problem.getCompoundStatistics();

			plotter.addSeriesPoint(fp.getBoardX(), executiontime1, cs2.getExecutionTime());
		}

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
