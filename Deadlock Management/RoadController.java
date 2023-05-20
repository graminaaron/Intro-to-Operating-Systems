/**
 * RoadController.java
 *
 * This class simulates delock avoidance by starting and 
 * controlling villager usage of a virtual road.
 *
 *
 * Written by:
 * Aaron Garcia Student ID: 030556771
 */

import java.util.concurrent.*;
 
public class RoadController
{  
	
	public static void main(String args[])
	{
		// Initialize the locks
		Semaphore road = new Semaphore(1);
		
		// Initialize the West Villagers
		West_village villager1 = new West_village("Julius", 1, road);
		West_village villager2 = new West_village("Mary", 2, road);
		West_village villager3 = new West_village("Sue", 3, road);
		
		// Initialize the East Villagers
		East_village villager4 = new East_village("Maki", 1, road);
		East_village villager5 = new East_village("Antonio", 2, road);
		East_village villager6 = new East_village("Ty", 3, road);
		
		// Run each villagers task (thread), alternating between the two villages
		villager1.start();
		villager4.start();
		villager2.start();
		villager5.start();
		villager3.start();
		villager6.start();
		
		// End program confirmation
		System.out.println("Main thread has ended!");
		
		
	}
}
