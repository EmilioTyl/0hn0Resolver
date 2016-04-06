package itba.edu.ar.heuristic.impl;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import itba.edu.ar.heuristic.Heuristic;
import itba.edu.ar.model.Direction;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.model.Token;

public class H2 extends Heuristic{

	private static List<Direction> directions = new LinkedList<Direction>();
	
	static{
		directions.add(Direction.RIGHT);
		directions.add(Direction.DOWN);
	}

	
	
	@Override
	public int getHeuristicValue(OhnO state) {

		Set<TokenAndDirection> tokenAndDirections = new HashSet<TokenAndDirection>();
		if(!fillTokenAndDirection(state, tokenAndDirections))
			return Integer.MAX_VALUE;
				
		removeInteresectingNumbers(state,tokenAndDirections);
		
		
	}
	
	
	private void removeInteresectingNumbers(OhnO state, Set<TokenAndDirection> tokenAndDirections) {
		Token[][] board = state.getBoard();
		
		
		
		
	}
	
	private void weight(Token[][] board,Point position, Direction direction,Set<TokenAndDirection> tokenAndDirections) {
		int x = position.x;
		int y = position.y;

		x += direction.getX();
		y += direction.getY();

		while (canSee(board,x, y)) {
			x += direction.getX();
			y += direction.getY();
			tokenAndDirections
		}

	}



	private boolean fillTokenAndDirection(OhnO state,Set<TokenAndDirection> tokenAndDirections){
		Token[][] board = state.getBoard();
		
		for(Point numberPosition : state.getNumbersPositions()){
			int weight = weight(board, numberPosition);
			if(weight>0){
				for(Direction direction : directions){
					tokenAndDirections.add(new TokenAndDirection(board[numberPosition.x][numberPosition.y, direction));
				}
			}else if(weight<0){
				return false;
			}
		}
		
		return true;
	}
	
	
	
	class TokenAndDirection{
		private Token token;
		private Direction direction;
		
		public TokenAndDirection(Token token, Direction direction) {
			super();
			this.token = token;
			this.direction = direction;
		}
		
		

		public Token getToken() {
			return token;
		}



		public Direction getDirection() {
			return direction;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((direction == null) ? 0 : direction.hashCode());
			result = prime * result + ((token == null) ? 0 : token.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TokenAndDirection other = (TokenAndDirection) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (direction != other.direction)
				return false;
			if (token == null) {
				if (other.token != null)
					return false;
			} else if (!token.equals(other.token))
				return false;
			return true;
		}

		private H2 getOuterType() {
			return H2.this;
		}
		
		
	}
	

}
