package itba.edu.ar.input.console;

import java.io.IOException;

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

		String algorithmName = args[0];
		String heuristicName = args[1];
		String board = args[2];
		FileParser fp = new FileParser();
		fp.parseFile(board);
		Algorithm algorithm = null;
		Heuristic heuristic = null;
		GPSStatistics statistics = new BasicStatistics("Phase 1");

		switch (heuristicName) {
		case "h1":
			heuristic = new H1();
			break;
		case "h2":
			heuristic = new H2();
			break;
		}

		switch (algorithmName) {
		case "dfs":
			algorithm = new DFS();
			break;
		case "bfs":
			algorithm = new BFS();
			break;
		case "astar":
			algorithm = new AStar();
			break;
		case "greedy":
			algorithm = new Greedy();
			break;
		case "idfs":
			algorithm = new IDFS();
			break;
		}

		GPSProblem problem = getProblem(algorithm, heuristic, fp,statistics);

		OhnO state = ((OhnO) problem.getInitState());
		System.out.println("Running 0hn0 board using:\n\tAlgorithm: " + algorithm.toString() + "\n\tHeuristic: "
				+ heuristic.toString() + "\n\tBoard dimensions: " + state.getBoardX() + "x" + state.getBoardY());

		algorithm.execute(problem,statistics);

	}

	private static GPSProblem getProblem(Algorithm algorithm, Heuristic heuristic, FileParser fp, GPSStatistics statistics) {
		return new OhnOProblem(algorithm, heuristic, fp.getBoardX(), fp.getBoardY(), fp.getTokens(),statistics);
	}

}
