package itba.edu.ar.input.console;

import java.io.IOException;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.algorithm.impl.AStar;
import itba.edu.ar.algorithm.impl.BFS;
import itba.edu.ar.algorithm.impl.DFS;
import itba.edu.ar.algorithm.impl.Greedy;
import itba.edu.ar.algorithm.impl.IDFS;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.input.FileParser;
import itba.edu.ar.resolver.OhnOProblem;

public class ConsoleParser {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

		String algorithmName = args[0];
		String board = args[1];
		FileParser fp = new FileParser();
		fp.parseFile(board);
		Algorithm algorithm = null;
		
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
		
		GPSProblem problem = getProblem(algorithm,fp);
		algorithm.execute(problem);

	}
	
	private static GPSProblem getProblem(Algorithm algorithm,FileParser fp){
		return new OhnOProblem(algorithm, fp.getBoardX(), fp.getBoardY(), fp.getTokens());
	}


}
