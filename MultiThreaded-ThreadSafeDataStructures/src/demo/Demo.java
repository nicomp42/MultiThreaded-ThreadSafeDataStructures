/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package demo;

import java.util.ArrayList;
import java.util.List;

public class Demo extends Thread {

    private int threadNumber, start, end, increment;
    private List<Integer> target;
    public Demo(int threadNumber, int start, int end, List<Integer> target, int increment) {
    	this.threadNumber = threadNumber;
    	this.start = start;
        this.end = end;			// This is a value.
        this.target = target;	// This is a reference. All threads are still using the same data structure.
        this.increment = increment;
    }

    @Override
    public void run() {
        System.out.println("Thread #" + threadNumber + " starting...");
        for(int i = start; i <= end; i++) {
        	int tmp;
        	//**************************************************************************************************
        	// Somewhere in these 2 lines of code the logic will enter a race condition between threads.
        	// All we're doing is incrementing the element in the data structure. 
        	// Ways to fix it:
        	// 1. Divide up the work so no two threads are accessing the same ArrayList element at the same time.
        	// 2. Use a thread-safe data structure: see https://stackoverflow.com/questions/2444005/how-do-i-make-my-arraylist-thread-safe-another-approach-to-problem-in-java
        	// 3. Use a monitor lock or synchronized keyword to prevent multiple threads from entering this critical section.
        	// 4. Probably more ways too: https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
        	tmp = target.get(i);
    		target.set(i, tmp + increment);
    		// *************************************************************************************************
    		
 //       	try {sleep(1);} catch (Exception ex) {}
        }
        System.out.println("Thread #" + threadNumber + " complete.");
    }
}
