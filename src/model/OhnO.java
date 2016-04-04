package model;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class OhnO implements GPSState {

	private List<Point> numbersPositions;
	private Token[][] board;
	private TokenFactory tokenFactory;
	private int boardX;
	private int boardY;

	public OhnO(int boardX, int boardY, List<Entry<Point, Token>> tokens) {
		board = new Token[boardX][boardY];
		numbersPositions = new LinkedList<Point>();
		tokenFactory = new TokenFactory();

		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				board[i][j] = tokenFactory.getBlue();
			}
		}

		for (Entry<Point, Token> entry : tokens) {
			int x = (int) entry.getKey().getX();
			int y = (int) entry.getKey().getY();
			Token token = entry.getValue();

			board[x][y] = token;

			if (token.isNumber())
				numbersPositions.add(entry.getKey());
		}
	}

	private OhnO(Token[][] board, List<Point> numbersPositions, TokenFactory tokenFactory) {
		this.board = board;
		this.boardX = board[0].length;
		this.boardY = board.length;
		this.numbersPositions = numbersPositions;
		this.tokenFactory = tokenFactory;
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

	@Override
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

	private boolean canSee(int x, int y) {
		if (!insideBoundaries(x, y))
			return false;

		Token token = board[x][y];

		return !token.isRed();
	}

	private boolean insideBoundaries(int x, int y) {
		return x >= 0 && x < boardX && y >= 0 && y < boardY;
	}

	public GPSState placeRed(Point redPosition) throws NotAppliableException {

		if (board[redPosition.x][redPosition.y].isRed())
			throw new NotAppliableException();

		Token[][] ans = copy();
		ans[redPosition.x][redPosition.y] = tokenFactory.getRed();

		return new OhnO(ans, numbersPositions, tokenFactory);
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

}
