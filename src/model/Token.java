package model;

public class Token {
		
	private int value;
		
	public Token(int value){
			this.value=value;
	}
	
	public int getNumber(){
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
		return !isBlue() && !isRed();
	}
	
	public boolean isBlue(){
		return value==-1;
	}
	
	public boolean isRed(){
		return value==-2;
	}
	
	
		

}
