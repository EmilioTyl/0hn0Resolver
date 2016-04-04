package Resolver0hn0;

import java.awt.Point;
import java.util.List;

import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class Rule0hn0 implements GPSRule{
	List<Point> walls = null;
	int boundNeightbours = 0;

	
	public Rule0hn0(List<Point> walls) {
		this.walls = walls;
		for(Point w: walls){
			boundNeightbours += (Math.abs(w.x) + Math.abs(w.y) - 2);
		}

	}
	
	@Override
	public Integer getCost() {
		return 0;
	}

	@Override
	public String getName() {
		return "Minimize labeled tile, placing a wall at ";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		BoardState boardState = (BoardState) state;
		BoardState newBoardState = boardState.deepCopy();
		Tile minimizeTile = boardState.getTile();
	
		if(boundNeightbours > minimizeTile.getLabel())
			throw new NotAppliableException();
		Point newWallPos = null;
		for(Point w: walls){
			newWallPos = newBoardState.getTilePosition() + w;
			if(!newBoardState.putWall(newWallPos))
				throw new NotAppliableException();
		}
		if(boaedState.calculate)
		
		return newBoardState;
	}

}
