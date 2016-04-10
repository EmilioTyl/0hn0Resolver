package itba.edu.ar.test.parameters;

import java.util.List;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.heuristic.Heuristic;

public class AlgorithmAndHeuristic {

	private Algorithm algorithm;
	private Heuristic heuristic;
	private List<String> exceptions;
	
	public AlgorithmAndHeuristic(Algorithm algorithm, Heuristic heuristic,List<String> exceptions) {
		super();
		this.algorithm = algorithm;
		this.heuristic = heuristic;
		this.exceptions=exceptions;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public Heuristic getHeuristic() {
		return heuristic;
	}

	@Override
	public String toString() {
		return algorithm.toString() + " with " + heuristic.toString();
	}

	public List<String> getExceptions() {
		return exceptions;
	}

}
