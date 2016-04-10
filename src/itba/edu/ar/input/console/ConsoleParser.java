package itba.edu.ar.input.console;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.impl.AStar;
import itba.edu.ar.algorithm.impl.BFS;
import itba.edu.ar.algorithm.impl.DFS;
import itba.edu.ar.algorithm.impl.Greedy;
import itba.edu.ar.algorithm.impl.IDFS;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSStatistics;
import itba.edu.ar.gps.impl.BasicStatistics;
import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.heuristic.impl.H1;
import itba.edu.ar.heuristic.impl.H2;
import itba.edu.ar.input.FileParser;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.resolver.OhnO.OhnOProblem;

public class ConsoleParser {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
		try {

			int index = 0;
			String algorithmName = args[index++];
			String heuristicName = null;
			Algorithm algorithm = null;
			Heuristic heuristic = null;

			switch (algorithmName) {
			case "dfs":
				algorithm = new DFS();
				break;
			case "bfs":
				algorithm = new BFS();
				break;
			case "astar":
				algorithm = new AStar();
				heuristicName = args[index++];
				break;
			case "greedy":
				algorithm = new Greedy();
				heuristicName = args[index++];
				break;
			case "idfs":
				algorithm = new IDFS();
				break;
			}

			heuristic = getHeuristic(heuristicName);
			String board = args[index];
			FileParser fp = new FileParser();
			fp.parseFile(board);

			GPSStatistics statistics = new BasicStatistics("Phase 1");

			GPSProblem problem = getProblem(algorithm, heuristic, fp, statistics);

			OhnO state = ((OhnO) problem.getInitState());
			System.out.println(
					"Running 0hn0 board using:\n\tAlgorithm: " + algorithm.toString() + getHeuristicName(heuristic)
							+ "\n\tBoard dimensions: " + state.getBoardX() + "x" + state.getBoardY());

			algorithm.execute(problem, statistics);
		} catch (NoSuchFileException e) {
			System.out.println("The requested board does not exist");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Greedy/A* requires a heuristic");
		}
	}

	private static String getHeuristicName(Heuristic heuristic) {
		if (heuristic == null)
			return "";

		return "\n\tHeuristic: " + heuristic.toString();
	}

	private static Heuristic getHeuristic(String heuristicName) {
		if (heuristicName == null)
			return null;

		switch (heuristicName) {
		case "h1":
			return new H1();
		case "h2":
			return new H2();
		}

		return null;
	}

	private static GPSProblem getProblem(Algorithm algorithm, Heuristic heuristic, FileParser fp,
			GPSStatistics statistics) {
		return new OhnOProblem(algorithm, heuristic, fp.getBoardX(), fp.getBoardY(), fp.getTokens(), statistics);
	}

}
