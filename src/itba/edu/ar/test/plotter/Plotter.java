package itba.edu.ar.test.plotter;

import java.io.IOException;

public interface Plotter {

	public void addSeriesPoint(int boardDimension, Double... values);

	public void plot() throws IOException;

}
