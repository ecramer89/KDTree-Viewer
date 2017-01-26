package util;
import java.util.ArrayList;

public class Util {


	/*sorts an array of integers from smallest to largest*/
	public static void qSort(int[] values){
		qSort(values, 0,values.length);
	}



	/*insert val into a sorted list
	 */
	public static void insert(ArrayList<Integer> list, int val){
		//base case
		if(list.isEmpty()){
			list.add(val);
		}
		else {
			int index=findIndexToInsert(list, val, 0, list.size()-1);
			list.add(index,val);
		}
	}



	private static int findIndexToInsert(ArrayList<Integer> list, int val, int low, int high) {
		//base case; 1 element in list
		if(high-low==0){
			int i = list.get(low);
			if(val<=i) return low;
			return low+1;
		}
		
		//recursive case, split
		int midIdx=((high-low)/2)+low;
		int midVal= list.get(midIdx);
		if(midVal==val) return midIdx;
		if(midVal>val) return findIndexToInsert(list, val, low, midIdx);
		else return findIndexToInsert(list, val, midIdx+1, high);
	}








private static void qSort(int[] values, int low, int hi){
	if(hi-low<2) return;
	int pivotIndex=(int)Util.random(low, hi);
	int pivotValue=values[pivotIndex];

	partition(values, low, hi, pivotValue, pivotIndex);

	qSort(values, low, pivotIndex);
	qSort(values, pivotIndex, values.length);
}

public static void printArray(int[] vals){
	for(int i=0;i<vals.length;i++){
		System.out.print(vals[i]+" ");

	}
	System.out.println();
}

public static double random(int high, int low){
	return Math.random()*(high-low)+low;
}

private static void partition(int[] values, int low, int hi, int pivotValue, int pivotIndex){

	int fromBelow=low;
	int fromAbove=hi-1;

	while(fromBelow<=fromAbove){


		while(values[fromBelow]<pivotValue) fromBelow++;
		while(values[fromAbove]>pivotValue) fromAbove--; 


		if(fromBelow<=fromAbove){
			swap(values, fromBelow, fromAbove);
			fromBelow++;
			fromAbove--;
		}

	}


}


private static void swap(int[] values, int idxA, int idxB){
	int temp=values[idxA];
	values[idxA]=values[idxB];
	values[idxB]=temp;
}

}
