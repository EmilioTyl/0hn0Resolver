package itba.edu.ar.OhnOFinalStageResolver.rules;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.algorithm.cost.Cost;
import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.model.Token;
import itba.edu.ar.resolver.rules.OhnORule;

public class OhnOFinalStageRule {

	public static List<GPSRule> generateRules(OhnO state, Cost cost){
	
		List<GPSRule> rules = new LinkedList<GPSRule>();
		Token[][] board = state.getBoard();
		int boardX = board[0].length;
		int boardY = board.length;
				
		for(int x=0;x<boardX;x++){
			for(int y=0;y<boardY;y++){
				if(board[x][y].isFloor())
				{
					rules.add(new OhnORule(new Point(x,y),cost.getCost()));
				}
				
			}
		}
		return rules;
		
	}

}
