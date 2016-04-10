package itba.edu.ar.gps;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.gps.api.GPSState;
import itba.edu.ar.gps.api.GPSStatistics;
import itba.edu.ar.gps.exception.NotAppliableException;

public class GPSEngine {

	private Queue<GPSNode> open;
	private Map<GPSState, Integer> bestCosts = new HashMap<GPSState, Integer>();
	private int maxDepth;
	private boolean maxDepthActivated;

	private GPSProblem problem;
	private GPSStatistics statistics;

	public GPSEngine(Comparator<GPSNode> comparator, GPSProblem problem) {
		open = new PriorityQueue<>(comparator);
		this.problem = problem;
	}

	public GPSEngine(Comparator<GPSNode> comparator, GPSProblem problem, GPSStatistics statistics) {
		open = new PriorityQueue<>(comparator);
		this.problem = problem;
		this.statistics = statistics;
	}

	public GPSEngine(Comparator<GPSNode> comparator, GPSProblem problem, GPSStatistics statistics, int maxDepth) {
		open = new PriorityQueue<>(comparator);
		this.problem = problem;
		this.maxDepth = maxDepth;
		this.maxDepthActivated = true;
	}

	public boolean engine() {

		statistics.startSearch();
		GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
		boolean finished = false;
		boolean failed = false;
		open.add(rootNode);
		while (!failed && !finished) {
			if (open.size() <= 0) {
				failed = true;
			} else {
				GPSNode currentNode = open.remove();
				statistics.analizeNode();
				if (problem.isGoal(currentNode.getState())) {
					finished = true;
					System.out.println(currentNode.getSolution());
					statistics.endSearch();
					statistics.goalNode(currentNode);
					problem.goalState(currentNode.getState());
				} else {
					if (!(maxDepthActivated && currentNode.getDepth() >= maxDepth)) {
						if (explode(currentNode)) {
							statistics.explodeNode();
						}
					}
				}
			}
		}

		if (failed) {
			statistics.solutionNotFound();
		}

		return finished;
	}

	private boolean explode(GPSNode node) {
		if (bestCosts.containsKey(node.getState()) && bestCosts.get(node.getState()) <= node.getCost()) {
			return false;
		}
		updateBest(node);
		if (problem.getRules() == null) {
			System.err.println("No rules!");
			return false;
		}
		for (GPSRule rule : problem.getRules()) {
			GPSState newState = null;
			try {
				newState = rule.evalRule(node.getState());
			} catch (NotAppliableException e) {
				// Do nothing
			}
			if (newState != null && isBest(newState, node.getCost() + rule.getCost())) {
				GPSNode newNode = new GPSNode(newState, node.getCost() + rule.getCost());
				newNode.setParent(node);
				open.add(newNode);
			}
		}
		return true;
	}

	private boolean isBest(GPSState state, Integer cost) {
		return !bestCosts.containsKey(state) || cost < bestCosts.get(state);
	}

	private void updateBest(GPSNode node) {
		bestCosts.put(node.getState(), node.getCost());
	}

}
