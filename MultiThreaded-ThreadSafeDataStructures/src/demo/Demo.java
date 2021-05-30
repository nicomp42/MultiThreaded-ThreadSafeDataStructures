/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package demo;

import java.util.ArrayList;

public class Demo extends Thread {

    private int threadNumber, start, end, increment;
    private ArrayList<Integer> target;
    public Demo(int threadNumber, int start, int end, ArrayList<Integer> target, int increment) {
    	this.threadNumber = threadNumber;
    	this.start = start;
        this.end = end;			// This is a value.
        this.target = target;	// This is a reference.
        this.increment = increment;
    }

    @Override
    public void run() {
        System.out.println("Thread #" + threadNumber + " starting...");
        for(int i = start; i <= end; i++) {
        	int tmp;
        	
        	// Somewhere in these 2 lines of code the logic will enter a race condition between threads.
        	// All we're doing is incrementing the element in the ArrayList. 
        	tmp = target.get(i);
    		target.set(i, tmp + increment);
    		
 //       	try {sleep(1);} catch (Exception ex) {}
        }
        System.out.println("Thread #" + threadNumber + " complete.");
    }
}
