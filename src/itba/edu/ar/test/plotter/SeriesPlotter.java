package itba.edu.ar.test.plotter;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class SeriesPlotter implements Plotter {

	private HashMap<Integer, SeriesPoint> series = new HashMap<Integer, SeriesPoint>();

	@Override
	public void addSeriesPoint(int boardDimension, Double valueA1, Double valueA2) {

		if (!series.containsKey(boardDimension)) {
			series.put(boardDimension, new SeriesPoint(valueA1, valueA2));
		} else {
			series.get(boardDimension).addValue(valueA1, valueA2);
		}
	}
	
	public void plot(){
		// Create Chart
		final XYChart chart = new XYChartBuilder().width(600).height(400).title("Order Index by Nois")
				.xAxisTitle("Noise").yAxisTitle("Order Index").build();

		// Customize Chart
		chart.getStyler().setLegendPosition(LegendPosition.OutsideE);

		List<Integer> boardDimensions = asSortedList(series.keySet());
		List<Double> a1 = new LinkedList<Double>();
		List<Double> a2 = new LinkedList<Double>();

		for (Integer boardDimension : boardDimensions) {
			SeriesPoint sp = series.get(boardDimension);
			a1.add(sp.getAvgValueA1());
			a2.add(sp.getAvgValueA2());
		}
		
		double[] x = IntegerlistToArray(boardDimensions);
		double[] yA1 = DoublelistToArray(a1);
		double[] yA2 = DoublelistToArray(a2);
		
		chart.addSeries("A1",x ,yA1);
		chart.addSeries("A2",x ,yA2);
		 
		new SwingWrapper(chart).displayChart();
		
		// Show it
		//BitmapEncoder.saveBitmapWithDPI(chart, path + "IndexByNoise", BitmapFormat.PNG, 300);
	}
	
	private double[] DoublelistToArray(List<Double> list) {
		double[] ans = new double[list.size()];
		int i = 0;
		for (Double value : list) {
			ans[i] = value.doubleValue();
			i++;
		}
		return ans;
	}

	private double[] IntegerlistToArray(List<Integer> list) {
		double[] ans = new double[list.size()];
		int i = 0;
		for (Integer value : list) {
			ans[i] = value.intValue();
			i++;
		}
		return ans;
	}

	
	public static
	<T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new LinkedList<T>(c);
	  java.util.Collections.sort(list);
	  return list;
	}

	private class SeriesPoint {

		private Double avgValueA1;
		private Double avgValueA2;
		private int simulationTimes;

		public SeriesPoint(Double valueA1, Double valueA2) {
			super();
			this.avgValueA1 = valueA1;
			this.avgValueA2 = valueA2;
			this.simulationTimes = 1;
		}

		public void addValue(Double valueA1, Double valueA2) {
			avgValueA1 += valueA1;
			avgValueA2 += valueA2;
			simulationTimes++;
		}

		public double getAvgValueA1() {
			return avgValueA1 / (double) simulationTimes;
		}

		public double getAvgValueA2() {
			return avgValueA2 / (double) simulationTimes;
		}

	}

}
