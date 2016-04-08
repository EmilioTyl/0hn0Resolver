package itba.edu.ar.resolver.OhnO;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.gps.api.GPSState;
import itba.edu.ar.gps.api.GPSStatistics;
import itba.edu.ar.gps.impl.CompoundStatistics;
import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.model.Token;
import itba.edu.ar.resolver.OhnO.rules.OhnORuleFactory;
import itba.edu.ar.resolver.OhnOFinalStage.OhnOFinalStageProblem;

public class OhnOProblem implements GPSProblem {

	private OhnORuleFactory ruleFactory;
	private Map<Point, Token> tokens;
	private Map<GPSState, Integer> heuristicValues = new HashMap<GPSState, Integer>();
	private Algorithm algorithm;
	private int boardX;
	private int boardY;
	private Heuristic heuristic;
	private GPSStatistics statistics;

	public OhnOProblem(Algorithm algorithm, Heuristic heuristic, int boardX, int boardY, Map<Point, Token> tokens,GPSStatistics statistics) {
		this.algorithm = algorithm;
		this.tokens = tokens;
		this.boardX = boardX;
		this.boardY = boardY;
		this.heuristic = heuristic;
		this.statistics=statistics;
	}

	@Override
	public GPSState getInitState() {
		OhnO state = new OhnO(boardX, boardY, tokens);
		ruleFactory = new OhnORuleFactory(state, algorithm.getCost());
		return state;
	}

	@Override
	public boolean isGoal(GPSState state) {
		return ((OhnO) state).isGoal();
	}

	@Override
	public List<GPSRule> getRules() {
		return ruleFactory.getRules();
	}

	@Override
	public Integer getHValue(GPSState state) {
		if (!heuristicValues.containsKey(state))
			heuristicValues.put(state, heuristic.getHeuristicValue((OhnO) state));

		return heuristicValues.get(state);
	}

	@Override
	public void goalState(GPSState state) {
		GPSStatistics newStatistics = new CompoundStatistics(statistics,"Phase 2");
		GPSProblem problem = new OhnOFinalStageProblem(state, algorithm.getCost(),newStatistics);
		algorithm.execute(problem,newStatistics);
	}

}
