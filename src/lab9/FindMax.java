package lab9;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax();
	}
	
	public class findMax_r implements Runnable {
	    private volatile int max;
	    private int begin;
	    private int end;

	    public findMax_r(int begin, int end) {
	        this.begin = begin;
	        this.end = end;
	    }
	    
	    public int getMax() {
	        return max;
	    }

	    public void run() {
	    	max = findMax(begin, end);
	    }
	}
	
	public void printMax() {
		// this is a single threaded version
		//int max = findMax(0, array.length - 1);
		//System.out.println("the max value is " + max);
		// this is multi-threaded version
		ArrayList<Integer> result = new ArrayList<>();
		findMax_r t1_run = new findMax_r(0,29);
		Thread t1 = new Thread(t1_run);
		findMax_r t2_run = new findMax_r(30,59);
		Thread t2 = new Thread(t2_run);
		findMax_r t3_run = new findMax_r(60,89);
		Thread t3 = new Thread(t3_run);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result.add(t1_run.getMax());
		result.add(t2_run.getMax());
		result.add(t3_run.getMax());

		System.out.println("the max value is " + Collections.max(result));
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
