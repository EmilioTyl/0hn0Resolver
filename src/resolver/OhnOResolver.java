package resolver;

import java.util.List;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import model.OhnO;
import resolver.rules.OhnORuleFactory;

public class OhnOResolver implements GPSProblem{

	private OhnORuleFactory ruleFactory; 
	
	
	@Override
	public GPSState getInitState() {
		OhnO state = new OhnO(5,5);
		ruleFactory = new OhnORuleFactory(state);
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
		return null;
	}
	
	private Integer h1(GPSState state){
		
	}
	
	 
	

	
	
	
}
