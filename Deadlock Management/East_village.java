	/*
	 * East_village.java
	 * 
	 * This class defines the name, tasks of a villager.
	 * When the primary function 'run()' is called, it uses a semaphore to 
	 * facilitate the safe completion of given task. It then relinquish 
	 * The lock for the use of the next villager.
	 *
	 * Written by:
	 * Aaron Garcia Student ID: 030556771
	 */

import java.util.Random;
import java.util.concurrent.Semaphore;

public class East_village extends Thread
{
	
	private String[] village_tasks = {"Hunting for Food", "Trading with the West Village", "Scouting the Surrounding Land"};
	private String cur_task;
	private String name;
	Semaphore traveling;
	
	public East_village(String villager, int task_num, Semaphore road)
	{
		 name = villager;
		 traveling = road;
		// select task from village task list
		 cur_task = village_tasks[(int)((task_num % 3))];
	}
	
	public void run()
	{
		synchronized (traveling)
		{
			// Randomization handling
			Random rand = new Random(System.currentTimeMillis());
			// Error handling
			try 
			{
				traveling.acquire();
				// Semaphore lock acquired
				System.out.println("East Villager: " + name + " is now traveling to perform their task.");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			// Error handling
			try
			{
				// Starting task notification
				System.out.println(name + " is now currently " + cur_task + "...");
				// Each task should take 1-3 seconds
				Thread.sleep((long)(rand.nextInt(1, 4) * 1000));
				// Finishing task notification
				System.out.println(name + " has finished their task and is now returning to the East Village!");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			traveling.release();
			// Semaphore lock released
			System.out.println(name + " returned to East Village");
		}
	}
}