/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 *
 * Written by:
 * Aaron Garcia Student ID: 030556771
 */


public class DiningPhilosophers
{  
   public static void main(String args[])
   {
	   //initialize the server
	   DiningServerImpl server = new DiningServerImpl();
	   //initialize the 5 philosophers
	   for(int i = 0; i < 5; i++) 
	   {
		   new Philosopher(i, server).run();
	   }
   }
}
