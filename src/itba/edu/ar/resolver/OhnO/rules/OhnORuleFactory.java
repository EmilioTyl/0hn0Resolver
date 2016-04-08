package itba.edu.ar.resolver.OhnO.rules;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.model.Direction;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.model.Token;

public class OhnORuleFactory {

	private List<GPSRule> rules = new LinkedList<GPSRule>();

	public OhnORuleFactory(OhnO state, Cost cost) {
		Set<OhnORule> rules = new HashSet<OhnORule>();

		Token[][] tokens = state.getBoard();
		List<Point> numbersPositions = state.getNumbersPositions();
		maybeCreateRules(numbersPositions, tokens, rules,cost);
		fillRulesList(rules);
	}

	private void fillRulesList(Set<OhnORule> rules) {
		for (OhnORule rule : rules) {
			this.rules.add(rule);
		}
	}

	public void maybeCreateRules(List<Point> numbersPositions, Token[][] tokens, Set<OhnORule> rules,
			Cost cost) {

		for (Point numberPosition : numbersPositions) {
			for (Direction direction : Direction.values()) {
				maybeCreateRule(numberPosition, direction, tokens, rules, cost);
			}
		}
	}

	private void maybeCreateRule(Point position, Direction direction, Token[][] tokens, Set<OhnORule> rules,
			Cost cost) {
		int x = position.x;
		int y = position.y;

		x += direction.getX();
		y += direction.getY();

		while (canSee(x, y, tokens)) {
			rules.add(new OhnORule(new Point(x, y), cost.getCost()));
			x += direction.getX();
			y += direction.getY();
		}
	}

	private boolean canSee(int x, int y, Token[][] tokens) {
		if (!insideBoundaries(x, y, tokens))
			return false;

		Token token = tokens[x][y];

		return !token.isWall();
	}

	private boolean insideBoundaries(int x, int y, Token[][] tokens) {
		int boardX = tokens[0].length;
		int boardY = tokens.length;

		return x >= 0 && x < boardX && y >= 0 && y < boardY;
	}

	public List<GPSRule> getRules() {
		return rules;
	}

}
