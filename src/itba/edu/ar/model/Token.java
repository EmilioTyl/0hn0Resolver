package itba.edu.ar.model;

public class Token {

	private int value;
	public static int FLOOR = -1;
	public static int WALL = -2;

	public Token(int value) {
		this.value = value;
	}

	public int getNumber() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
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
		Token other = (Token) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public boolean isNumber() {
		return !isFloor() && !isWall();
	}

	public boolean isFloor() {
		return value == FLOOR;
	}

	public boolean isWall() {
		return value == WALL;
	}

	@Override
	public String toString() {
		switch (value) {
		case -1:
			return "F";
		case -2:
			return "W";
		default:
			return value + "";
		}

	}

}
