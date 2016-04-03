package utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Matrix<T> {
	private List<List<T>> matrix;
	private int xSize;
	private int ySize;
	private Class<T> classT;
	
	public Matrix(int xSize, int ySize, Class<T> classT) throws InstantiationException, IllegalAccessException {
		super();
		this.xSize = xSize;
		this.ySize = ySize;
		this.classT = classT;
		clear();
	}

	public T get(int x,int y){
		return matrix.get(x).get(y);
	}
	
	public T get(Point position){
		return get(position.x,position.y);
	}
	
	public boolean exist(int x,int y){
		
		if(!insideBoundaries(x,y))
			throw new IllegalArgumentException("("+x+","+y+")");
		
		return !(matrix.size() < x || matrix.get(x).size() < y);
	}
	
	
	public boolean insideBoundaries(int x, int y) {
		return x >= 0 && x < xSize && y >=0 && y < ySize;
	}
	public boolean insideBoundaries(Point point) {
		return insideBoundaries(point.x, point.y);
	}

	public boolean exist(Point position) {
		return exist((int)position.getX(),(int)position.getY());
	}

	public void clear() throws InstantiationException, IllegalAccessException {
		matrix = new ArrayList<List<T>>(xSize);
		for(int x=0;x<xSize;x++)
		{
			matrix.add(x, new ArrayList<T>(ySize));
			for(int y=0;y<ySize;y++)
				matrix.get(x).add(classT.newInstance());
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix<T> other = (Matrix<T>) obj;
		for(int xIndex=0; xIndex< xSize; xIndex ++){
			for(int yIndex=0; yIndex< ySize; yIndex ++){
				Point point = new Point(xIndex, yIndex);
				if(!other.insideBoundaries(point) || !other.exist(point))
					return false;
				if(!get(point).equals(other.get(point)))
					return false;	
			}
		}
		return true;
	}
}
