package model;

import java.util.HashMap;

public class TokenFactory {
	
	private HashMap<Integer,Token> tokens = new HashMap<Integer,Token>();
	
	public TokenFactory(){
		tokens.put(-1,new Token(-1));
		tokens.put(-2,new Token(-2));
	}
	
	
	public Token getBlue(){
		return tokens.get(-1);
	}
	
	public Token getRed(){
		return tokens.get(-2);
	}
	
	public Token getNumber(int number){
		if(!tokens.containsKey(number))
			tokens.put(number,new Token(number));
		return tokens.get(number);	
	}
	
	

}
