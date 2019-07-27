/* Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package demo;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//demo();
		demoNotThreadSafe();
	}
	/**
	 * 4 threads used to initialize an ArrayList. Each thread processes a chunk of the ArrayList and it works fine.
	 */
	public static void demo() {
		ArrayList<Integer> cannonFodder = new ArrayList<Integer>();
		/* The work is nicely divided up between the 4 threads. */
		int limitPerThread = 4000;
		int numThreads = 4;
		for (int i = 0; i < limitPerThread * numThreads; i++) {
			cannonFodder.add(0);
		}

		Demo d1, d2, d3, d4;
		d1 = new Demo(1,                  0,  limitPerThread      - 1, cannonFodder);
		d2 = new Demo(2, limitPerThread    , (limitPerThread * 2) - 1, cannonFodder);
		d3 = new Demo(3, limitPerThread * 2, (limitPerThread * 3) - 1, cannonFodder);
		d4 = new Demo(4, limitPerThread * 3, (limitPerThread * 4) - 1, cannonFodder);

		d1.start();
		d2.start();
		d3.start();
		d4.start();

		try { d1.join();} catch (Exception ex) {}
		try { d2.join();} catch (Exception ex) {}
		try { d3.join();} catch (Exception ex) {}
		try { d4.join();} catch (Exception ex) {}
		
		System.out.println("demo(): All threads are complete.");
		int errorCount = 0;
		for (int i = 0; i < limitPerThread * numThreads; i++) {
			if (cannonFodder.get(i) != i) {errorCount++;}
		}
		System.out.println("demo(): Error count = " + errorCount);
	}
	/**
	 * 4 threads used to initialize an ArrayList. Each thread processes all of the ArrayList and it doesn't work so well.
	 */
	public static void demoNotThreadSafe() {
		ArrayList<Integer> cannonFodder = new ArrayList<Integer>();
		/* The work is nicely divided up between the 4 threads. */
		int limit = 4_000_000;
		for (int i = 0; i < limit; i++) {
			cannonFodder.add(i);
		}
		System.out.println("demoNotThreadSafe(): ArrayList initialized");

		Demo d1, d2, d3, d4;
		d1 = new Demo(1,                  0,  limit - 1, cannonFodder);
		d2 = new Demo(2,                  0,  limit - 1, cannonFodder);
		d3 = new Demo(3,                  0,  limit - 1, cannonFodder);
		d4 = new Demo(4,                  0,  limit - 1, cannonFodder);

		d1.start();
		d2.start();
		d3.start();
		d4.start();

		try { d1.join();} catch (Exception ex) {}
		try { d2.join();} catch (Exception ex) {}
		try { d3.join();} catch (Exception ex) {}
		try { d4.join();} catch (Exception ex) {}
		
		System.out.println("demoNotThreadSafe(): All threads are complete.");
		int errorCount = 0;
		for (int i = 0; i < limit; i++) {
			if (cannonFodder.get(i) != -i) {errorCount++;}
		}
		System.out.println("demoNotThreadSafe(): Error count = " + errorCount);
	}
}
