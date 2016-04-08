package itba.edu.ar.resolver.rules;

import java.awt.Point;

import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.gps.api.GPSState;
import itba.edu.ar.gps.exception.NotAppliableException;
import itba.edu.ar.model.OhnO;

public class OhnORule implements GPSRule{

	private static String PRE_NAME="Place wall at ";
	private Point wallPosition;
	private String name;
	private int cost;
	
	public OhnORule(Point redPosition,int cost) {
		this.wallPosition=redPosition;
		this.name=PRE_NAME;
		this.cost=cost;
	}
	
	@Override
	public Integer getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return name+"("+wallPosition.x+","+wallPosition.y+")";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		OhnO board = (OhnO) state;
		return board.placeWall(wallPosition,getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wallPosition == null) ? 0 : wallPosition.hashCode());
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
		OhnORule other = (OhnORule) obj;
		if (wallPosition == null) {
			if (other.wallPosition != null)
				return false;
		} else if (!wallPosition.equals(other.wallPosition))
			return false;
		return true;
	}
	
	
	
	

}
