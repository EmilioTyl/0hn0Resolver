package itba.edu.ar.model;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import itba.edu.ar.gps.api.GPSState;
import itba.edu.ar.gps.exception.NotAppliableException;

public class OhnO implements GPSState {

	private List<Point> numbersPositions;
	private Token[][] board;
	private TokenFactory tokenFactory = TokenFactory.getInstance();
	private int boardX;
	private int boardY;
	private String move;

	public OhnO(int boardX, int boardY, Map<Point, Token> tokens) {
		board = new Token[boardX][boardY];
		numbersPositions = new LinkedList<Point>();
		this.boardX = boardX;
		this.boardY = boardY;

		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				board[i][j] = tokenFactory.getFloor();
			}
		}

		for (Entry<Point, Token> entry : tokens.entrySet()) {
			int x = (int) entry.getKey().getX();
			int y = (int) entry.getKey().getY();
			Token token = entry.getValue();

			board[x][y] = token;

			if (token.isNumber())
				numbersPositions.add(entry.getKey());
		}
		
	}

	private OhnO(Token[][] board, List<Point> numbersPositions, String move) {
		this.board = board;
		this.boardX = board[0].length;
		this.boardY = board.length;
		this.numbersPositions = numbersPositions;
		this.move=move;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
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
		OhnO other = (OhnO) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	public boolean isGoal() {
		for (Point numberPosition : numbersPositions) {
			if (weight(numberPosition) != 0)
				return false;
		}
		return true;
	}

	public int weight(Point position) {
		int ans = 0;
		for (Direction direction : Direction.values()) {
			ans += weight(position, direction);
		}
		return ans - board[position.x][position.y].getNumber();
	}
	
	private int weight(Point position, Direction direction) {
		int x = position.x;
		int y = position.y;

		int ans = 0;
		x += direction.getX();
		y += direction.getY();

		while (canSee(x, y)) {
			x += direction.getX();
			y += direction.getY();
			ans++;
		}
		return ans;
	}
	
	public boolean canSee(int x, int y) {
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


	public GPSState placeWall(Point wallPosition, String move) throws NotAppliableException {

		if (board[wallPosition.x][wallPosition.y].isWall())
			throw new NotAppliableException();

		Token[][] ans = copy();
		ans[wallPosition.x][wallPosition.y] = tokenFactory.getWall();

		return new OhnO(ans, numbersPositions,move);
	}

	private Token[][] copy() {
		Token[][] ans = new Token[boardX][boardY];
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				ans[i][j] = board[i][j];
			}
		}
		return ans;
	}

	public Token[][] getBoard() {
		return board;
	}

	public List<Point> getNumbersPositions() {
		return numbersPositions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(move!=null){
			sb.append(move).append("\n");
		}
		
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		sb.append("\n").append("---------------").append("\n");
		String ans = sb.toString();
		
		//The string is reseted for the OhnOFinalStageProblem's initial state.
		move="";
		
		return ans;
	}

	private boolean hasIsolatedFloors() {
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				if (board[i][j].isFloor() && isIsolated(i, j))
					return true;
			}
		}
		return false;
	}
	
	private int getIsolatedFloorsQuantity() {
		int ans = 0;
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				if (board[i][j].isFloor() && isIsolated(i, j))
					ans++;
			}
		}
		return ans;
	}

	private boolean isIsolated(int i, int j) {
		for (Direction direction : Direction.values()) {
			if(!isIsolated(i,j, direction))
					return false;
		}
		return true;
	}

	private boolean isIsolated(int x, int y, Direction direction) {

		x += direction.getX();
		y += direction.getY();

		while (canSee(x, y)) {
			if(board[x][y].isNumber())
				return false;
			x += direction.getX();
			y += direction.getY();
		}
		return true;
	}

	public boolean isFinalGoal() {
		return !hasIsolatedFloors();		
	}

	public int hFinalGoal() {
		return getIsolatedFloorsQuantity();
	}
	
	public int getBoardX(){
		return boardX;
	}
	
	public int getBoardY(){
		return boardY;
	}

}
