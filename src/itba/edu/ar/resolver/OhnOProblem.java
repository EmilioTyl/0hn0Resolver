package itba.edu.ar.resolver;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.gps.api.GPSState;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.model.Token;
import itba.edu.ar.model.TokenFactory;
import itba.edu.ar.resolver.cost.Cost;
import itba.edu.ar.resolver.rules.OhnORuleFactory;

public class OhnOProblem implements GPSProblem{

	private OhnORuleFactory ruleFactory; 
	private Map<Point,Token> tokens;
	private Map<GPSState,Integer> heuristicValues = new HashMap<GPSState,Integer>();
	private Cost cost;
	private int boardX;
	private int boardY;
	
	public OhnOProblem(Cost cost,int boardX, int boardY, Map<Point,Token> tokens){
		this.cost=cost;
		this.tokens=tokens;
		this.boardX=boardX;
		this.boardY=boardY;
	}
	
	@Override
	public GPSState getInitState() {
		OhnO state = new OhnO(boardX,boardY,tokens);
		ruleFactory = new OhnORuleFactory(state,cost);
		return state;
	}

	@Override
	public boolean isGoal(GPSState state) {
		return state.isGoal();
	}

	@Override
	public List<GPSRule> getRules() {
		return ruleFactory.getRules();
	}

	@Override
	public Integer getHValue(GPSState state) {
		if(!heuristicValues.containsKey(state))
			heuristicValues.put(state,((OhnO)state).h2());
		
		return heuristicValues.get(state);
	}


	@Override
	public void goalState(GPSState state) {
		((OhnO)state).flipIsolatedFloors();
	}
	
	 
	

	
	
	
}
