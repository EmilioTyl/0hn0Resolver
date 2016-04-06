package itba.edu.ar.input.console;

import java.io.IOException;

import itba.edu.ar.gps.GPSEngine;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.input.FileParser;
import itba.edu.ar.resolver.OhnOProblem;
import itba.edu.ar.resolver.algorithm.AStar;
import itba.edu.ar.resolver.algorithm.BFS;
import itba.edu.ar.resolver.algorithm.DFS;
import itba.edu.ar.resolver.algorithm.Greedy;
import itba.edu.ar.resolver.cost.ConstantCost;
import itba.edu.ar.resolver.cost.NoCost;

public class ConsoleParser {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

		String algorithm = args[0];
		String board = args[1];
		FileParser fp = new FileParser();
		fp.parseFile(board);

		switch (algorithm) {
		case "dfs":
			dfs(fp);
			break;
		case "bfs":
			bfs(fp);
			break;
		case "astar":
			aStart(fp);
			break;
		case "greedy":
			greedy(fp);
			break;
		case "idfs":
			idfs(fp);
			break;
		}

	}

	private static void idfs(FileParser fp) {
		GPSProblem problem = new OhnOProblem(new ConstantCost(), fp.getBoardX(), fp.getBoardY(), fp.getTokens());
		boolean finished = false;
		int iteration = 1;
		while (!finished) {
			GPSEngine engine = new GPSEngine(new DFS(), problem, iteration);
			finished = engine.engine();
			System.out.println("vuelta " + iteration);
			iteration++;
		}
	}

	private static void dfs(FileParser fp) {
		GPSProblem problem = new OhnOProblem(new ConstantCost(), fp.getBoardX(), fp.getBoardY(), fp.getTokens());
		GPSEngine engine = new GPSEngine(new DFS(), problem);
		engine.engine();
	}

	private static void greedy(FileParser fp) {
		GPSProblem problem = new OhnOProblem(new NoCost(), fp.getBoardX(), fp.getBoardY(), fp.getTokens());
		GPSEngine engine = new GPSEngine(new Greedy(problem), problem);
		engine.engine();

	}

	private static void aStart(FileParser fp) {
		GPSProblem problem = new OhnOProblem(new ConstantCost(), fp.getBoardX(), fp.getBoardY(), fp.getTokens());
		GPSEngine engine = new GPSEngine(new AStar(problem), problem);
		engine.engine();

	}

	private static void bfs(FileParser fp) {
		GPSProblem problem = new OhnOProblem(new ConstantCost(), fp.getBoardX(), fp.getBoardY(), fp.getTokens());
		GPSEngine engine = new GPSEngine(new BFS(), problem);
		engine.engine();

	}

}
