package itba.edu.ar.resolver.rules;

import java.awt.Point;

import itba.edu.ar.gps.api.GPSRule;
import itba.edu.ar.gps.api.GPSState;
import itba.edu.ar.gps.exception.NotAppliableException;
import itba.edu.ar.model.OhnO;

public class OhnORule implements GPSRule{

	private static String PRE_NAME="Place red token at ";
	private Point redPosition;
	private String name;
	private int cost;
	
	public OhnORule(Point redPosition,int cost) {
		this.redPosition=redPosition;
		this.name=PRE_NAME+redPosition;
		this.cost=cost;
	}
	
	@Override
	public Integer getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		OhnO board = (OhnO) state;
		return board.placeRed(redPosition);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((redPosition == null) ? 0 : redPosition.hashCode());
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
		if (redPosition == null) {
			if (other.redPosition != null)
				return false;
		} else if (!redPosition.equals(other.redPosition))
			return false;
		return true;
	}
	
	
	
	

}
