package itba.edu.ar.heuristic;

import java.awt.Point;

import itba.edu.ar.model.Direction;
import itba.edu.ar.model.OhnO;
import itba.edu.ar.model.Token;

public abstract class Heuristic {

	public abstract int getHeuristicValue(OhnO state);
	
	protected int weight(Token[][] board,Point position) {
		int ans = 0;
		for (Direction direction : Direction.values()) {
			ans += weight(board,position, direction);
		}
		return ans - board[position.x][position.y].getNumber();
	}

	private int weight(Token[][] board,Point position, Direction direction) {
		int x = position.x;
		int y = position.y;

		int ans = 0;
		x += direction.getX();
		y += direction.getY();

		while (canSee(board,x, y)) {
			x += direction.getX();
			y += direction.getY();
			ans++;
		}
		return ans;

	}

	protected boolean canSee(Token[][] board,int x, int y) {
		if (!insideBoundaries(board,x, y))
			return false;

		Token token = board[x][y];

		return !token.isWall();
	}

	private boolean insideBoundaries(Token[][] board,int x, int y) {
		int boardX = board[0].length;
		int boardY = board.length;
		
		return x >= 0 && x < boardX && y >= 0 && y < boardY;
	}

	
}
	
	

