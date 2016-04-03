package Resolver0hn0;
import gps.api.GPSState;
import utils.Matrix;

public class BoardState implements GPSState{
	private Matrix<Tile> board = null;
	
	public BoardState(Matrix<Tile> board) {
		this.board = board;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardState other = (BoardState) obj;
		if (!board.equals(other.board))
			return false;
		return true;
	}
	
	public Matrix<Tile> getBoard(){
		return board;
	}
	
	
	
}
