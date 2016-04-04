package resolver.rules;

import java.awt.Point;

import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;
import model.OhnO;

public class OhnORule implements GPSRule{

	private Point redPosition;
	private String name;
	
	public OhnORule(Point redPosition) {
		this.redPosition=redPosition;
		this.name="Place red token at "+redPosition;
	}
	
	@Override
	public Integer getCost() {
		return 1;
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
