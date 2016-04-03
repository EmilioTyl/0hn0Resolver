package Resolver0hn0;

import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class Rule0hn0 implements GPSRule{
	
	
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
		
		return null;
	}

}
