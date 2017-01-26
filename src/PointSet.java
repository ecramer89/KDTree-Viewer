import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import static util.Util.*; 

public class PointSet {
	public static final int LEFT_SPLIT = 0;
	public static final int RIGHT_SPLIT = LEFT_SPLIT+1;
	private int nDimensions; //the number of dimensions needed to characterize each point
	private Set<Point> points;
	private ArrayList<?>[] sortedPoints; //an array containing for each dimension an array list of points sorted by values along that dimension

	public PointSet(int nDimensions){
		this.nDimensions=nDimensions;
		points=new HashSet<Point>();
		sortedPoints=new ArrayList<?>[nDimensions];
		for(int i=0;i<sortedPoints.length;i++){
			sortedPoints[i]=new ArrayList<Point>();
		}
	}

	public void print(){
		System.out.println("PointSet");
		for(Point p : points){
			System.out.println(p.toString());
		}


		System.out.println("Sorted Lists");
		for(int i=0;i<sortedPoints.length;i++){
			System.out.println("Dimension: "+i);
			ArrayList<Point> list = (ArrayList<Point>)sortedPoints[i];
			for(Point p : list){
				System.out.println(p.toString());
			}
		}

	}


	public PointSet(int[][] points, int numDimensions) {
		this(numDimensions);
		addAll(points);
	}



	public void addAll(int[][] points){
		for(int i=0;i<points.length;i++){
			addPoint(new Point(points[i]));
		}
	}

	public int getNumDimensions() {
		return nDimensions;
	}


	public int getSize() {
		return points.size();
	}

	/* return the only point in a dataset of size 1*/
	public Point getPoint() {
		return points.iterator().next();
	}

	/*return the value of the point that is median with respect to its value on the splittingAxis*/
	public Point getMedianValue(int splittingAxis) {
		ArrayList<Point> sortedByDimension = (ArrayList<Point>)sortedPoints[splittingAxis];

		Point medianPoint= sortedByDimension.get(getMedianIndex(splittingAxis));
		return medianPoint;
	}


	private int getMedianIndex(int splittingAxis){
		return sortedPoints[splittingAxis].size()/2;
	}


	/*splits this PointSet into two halves around the median (neither includes the median), with respect to the splittingDimension, and returns the two halves in an array*/
	public PointSet[] getMedianSplit(int splittingAxis) {
		PointSet[] result=new PointSet[2];

		PointSet left=new PointSet(nDimensions);
		PointSet right=new PointSet(nDimensions);
		int medianIndex=getMedianIndex(splittingAxis);
		ArrayList<Point> sortedBy=(ArrayList<Point>)sortedPoints[splittingAxis];

		for(int i=0;i<sortedBy.size();i++){
			Point point=sortedBy.get(i);
			if(i<medianIndex){
				left.addPoint(point);
			}

			if(i>medianIndex) {
				right.addPoint(point);
			}

			//do not add the point at medianIndex to either
		}
		


		result[LEFT_SPLIT]=left;
		result[RIGHT_SPLIT]=right;
		return result;

	}



	/*adds the point to the pointSet.*/
	public void addPoint(Point point){
		if(point.numDimensions()==nDimensions){
			points.add(point);
			for(int i=0; i<nDimensions; i++){
				ArrayList<Point> sortedBy = (ArrayList<Point>)sortedPoints[i];
				insert(sortedBy, point, i);
			}
		}

	}


	/*insert the Point in place in the sorted array. Use dimension specified by i to find the appropriate place for Point*/
	private void insert(ArrayList<Point> sortedBy, Point point, int byDimension) {
		//base cases (instead of recursing, check the end points of the list- slightly faster)
		if(sortedBy.isEmpty() || sortedBy.get(sortedBy.size()-1).getValue(byDimension)<point.getValue(byDimension)) sortedBy.add(point);
		else{
			if(sortedBy.get(0).getValue(byDimension)>point.getValue(byDimension)){
				sortedBy.add(0,point);
			}
			else {
				int index=findIndexToInsert(sortedBy, point, byDimension, 0, sortedBy.size()-1);
				sortedBy.add(index,point);
			}

		}
	}

	private int findIndexToInsert(ArrayList<Point> list, Point point, int dimension, int low, int high) {
		if(high-low==0){
			int lowVal = list.get(low).getValue(dimension);
			if(lowVal<=point.getValue(dimension)) return low;
			return low+1;
		}

		//recursive case, split
		int midIdx=((high-low)/2)+low;
		int midVal= list.get(midIdx).getValue(dimension);
		int val=point.getValue(dimension);
		if(midVal==val) return midIdx;
		if(midVal>val) return findIndexToInsert(list, point, dimension, low, midIdx);
		else return findIndexToInsert(list, point, dimension, midIdx+1, high);

	}

	public int advanceDimension(int splittingDimension) {
		// TODO Auto-generated method stub
		return (splittingDimension+1)%nDimensions;
	}

	public int getFirstDimension() {

		return 0;
	}

















}
