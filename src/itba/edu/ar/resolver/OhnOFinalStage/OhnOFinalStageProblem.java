package itba.edu.ar.resolver.OhnOFinalStage;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import itba.edu.ar.algorithm.Algorithm;
import itba.edu.ar.gps.api.GPSProblem;
import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.gps.api.GPSState;
import itba.edu.ar.gps.api.GPSStatistics;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.resolver.OhnOFinalStage.rules.OhnOFinalStageRule;

public class OhnOFinalStageProblem implements GPSProblem{
		
	private GPSState state;
	private List<GPSRule> rules;
	private Algorithm algorithm;
	private Map<GPSState,Integer> heuristicValues = new HashMap<GPSState,Integer>();
	private GPSStatistics statistics;


	public OhnOFinalStageProblem(GPSState state,Algorithm algorithm) {
		this.state=state;
		this.algorithm=algorithm;
	}
	
	public OhnOFinalStageProblem(GPSState state,Algorithm algorithm,GPSStatistics statistics) {
		this.state=state;
		this.algorithm=algorithm;
		this.statistics=statistics;
	}
	
	
	@Override
	public GPSState getInitState() {
		return state;
	}

	@Override
	public boolean isGoal(GPSState state) {
		return ((OhnO)state).isFinalGoal();
	}

	@Override
	public List<GPSRule> getRules() {
		if(rules==null){
			rules = OhnOFinalStageRule.generateRules((OhnO) state, algorithm.getCost());
		}
		
		if(algorithm.shouldShuffleRules()){
			Collections.shuffle(rules);
		}
		
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		if(!heuristicValues.containsKey(state))
			heuristicValues.put(state,((OhnO)state).hFinalGoal());
		
		return heuristicValues.get(state);
	}

	@Override
	public void goalState(GPSState state) {
		if(statistics!=null)
		{
			statistics.printStatistics();
		}
	}
	
	 
	
}
