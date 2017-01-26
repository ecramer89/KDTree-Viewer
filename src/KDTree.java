import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Vector;

public class KDTree {
	private int numDimensions;
	private int splittingDimension; 
	private Point point; //null if left or right is not null (actual values stored in the leaves)
	private KDTree left;
	private KDTree right;


	public KDTree(int numDimensions){
		this.numDimensions=numDimensions;
	}


	/*to do, points need to know their splitting dimension. will probably need to store the dimensions for the tree,
	 * didn't think about adding points one by one
	 */
	private KDTree(Point point, int numDimensions){
		this(numDimensions);
		this.point=point;
	}

	private KDTree(Point point, int numDimensions, int splittingAxis){
		this(point, numDimensions);
		this.splittingDimension=splittingAxis;
	}



	public void buildTree(PointSet data) {
		if(data.getSize()>0){
			buildTree(data, data.getSize(), data.getNumDimensions(),data.getFirstDimension());
		}
	}

	private KDTree buildTree(PointSet pointSet, int nPoints, int nDimensions, int splittingDimension){

		if(nPoints==1) { //base case
			point=pointSet.getPoint();
			this.splittingDimension=splittingDimension;
		}else {

			//recursive case
			point=pointSet.getMedianValue(splittingDimension);
			this.splittingDimension=splittingDimension;

			PointSet[] split = pointSet.getMedianSplit(splittingDimension);
			PointSet leftSplit=split[PointSet.LEFT_SPLIT];
			PointSet rightSplit=split[PointSet.RIGHT_SPLIT];

			splittingDimension=pointSet.advanceDimension(splittingDimension);

			left= (leftSplit.getSize()==0? null: new KDTree(nDimensions).buildTree(leftSplit, leftSplit.getSize(), nDimensions,splittingDimension));
			right = (rightSplit.getSize()==0? null: new KDTree(nDimensions).buildTree(rightSplit, rightSplit.getSize(), nDimensions, splittingDimension));
		}
		return this;
	}


	public int getSize(){

		int leftSize=(left==null? 0: left.getSize());


		int rightSize=(right==null? 0 : right.getSize());

		return 1+leftSize+rightSize;
	}


	public int getDepth(){
		if(getSize()==0) return 0;
		else return 1+Math.max((left==null? 0 :left.getDepth()), (right==null? 0 : right.getDepth()));
	}


	public void print(){
		if(right!=null || left!=null) print(new StringBuffer());
		else System.out.print(point.toString());

	}


	private void print(StringBuffer tab){
		String myTab=tab.toString();
		tab.append(" ");
		StringBuffer rightCopy=new StringBuffer(tab.toString());
		if(left!=null) {
			left.print(tab);
		}
		System.out.println(myTab+point.toString());
		if(right!=null){
			right.print(rightCopy);
		}
	}



	public void paint(Graphics g, int canvasWidth, int canvasHeight){
		int nodeHeight=canvasHeight/getDepth();
		paint(g, canvasWidth, canvasHeight/getDepth(), canvasWidth/2, nodeHeight/2);
	}


	private void paint(Graphics g, int currWidth, int currHeight, int currX, int currY){

		if(point!=null) {
			point.paint(g, currX, currY, currWidth, currHeight);
		}

		int newWidth=currWidth/2;
		int newY=currY+currHeight;


		if(left!=null){
			left.paint(g, newWidth, currHeight, currX-newWidth/2, newY);
		}

		if(right!=null){
			right.paint(g, newWidth, currHeight, currX+newWidth/2, newY);
		}



	}


	/*insert the point into its proper place in the tree*/
	public void addPoint(Point newPoint) {

		if(point==null) point=newPoint;
		else {
			if(newPoint.getValue(splittingDimension)>=point.getValue(splittingDimension)){
				if(right==null){
					right=new KDTree(newPoint, numDimensions,getNextSplittingAxis(splittingDimension));
				} else right.addPoint(newPoint);
			}else {
				if(left==null){
					left=new KDTree(newPoint,numDimensions,getNextSplittingAxis(splittingDimension));
				}else left.addPoint(newPoint);
			}
		}

	}


	private int getNextSplittingAxis(int currSplittingAxis){
		return (currSplittingAxis+1)%numDimensions;
	}












}
