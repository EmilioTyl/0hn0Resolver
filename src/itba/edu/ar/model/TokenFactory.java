package itba.edu.ar.model;

import java.util.HashMap;

public class TokenFactory {
	
	private HashMap<Integer,Token> tokens = new HashMap<Integer,Token>();
	private static TokenFactory instance;
	
	private TokenFactory(){
		tokens.put(Token.FLOOR,new Token(Token.FLOOR));
		tokens.put(Token.WALL,new Token(Token.WALL));
	}
	
	public static TokenFactory getInstance(){
		if(instance==null)
			instance = new TokenFactory();
		return instance;
	}
	
	
	public Token getFloor(){
		return tokens.get(Token.FLOOR);
	}
	
	public Token getWall(){
		return tokens.get(Token.WALL);
	}
	
	public Token getNumber(int number){
		if(!tokens.containsKey(number))
			tokens.put(number,new Token(number));
		return tokens.get(number);	
	}
	
	

}
