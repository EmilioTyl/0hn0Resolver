package Resolver0hn0;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public class Resolver0hn0 implements GPSProblem {
	public static void main(String[] args) {
		Queue<GPSNode> open = new PriorityQueue<GPSNode>();
		new GPSEngine().engine(new Resolver0hn0(), SearchStrategy.GREEDY, open );
	}

	@Override
	public GPSState getInitState() {
	
		
	}

	@Override
	public boolean isGoal(GPSState state) {
		state.isFinal();
	}

	@Override
	public List<GPSRule> getRules() {
		List<GPSRule> rules = new LinkedList<GPSRule>();
		List<Point> directions = new LinkedList<Point>();
		directions.add(new Point(0,1));
		directions.add(new Point(-1,0));
		directions.add(new Point(0,-1));
		directions.add(new Point(1,0));
		List<Point> walls =  new ArrayList<Point>();
		
		for(int maxVal = 0; maxVal <=5; maxVal ++){
			position(walls, 5,0, rules, directions, 4);
		}
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public  void position( List<Point> walls, int maxVal, int used, List<GPSRule> rules, List<Point> directions, int count){
		if(count == 0){
			rules.add(new Rule0hn0(deepCopyPoints(walls)));
			return;
		}
		for(int w = maxVal-used; w >= 0; w--){
			Point p = new Point(directions.get(count).x *w, directions.get(count).y * w);
			walls.add(p);
			position(walls, 5, used+w,rules, directions, count - 1);
			walls.remove(p);
		}
		
	}

	private List<Point> deepCopyPoints(List<Point> walls) {
		List<Point> copy = new ArrayList();
		for(Point p : walls){
			copy.add(p);
		}
		return copy;
	}
}
