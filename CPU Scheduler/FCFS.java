/**
 * FCFS scheduling algorithm.
 * 
 * Written by:
 * Aaron Garcia Student ID: 030556771
 */
 
import java.util.*;

//Your code here
public class FCFS implements Algorithm 
{
    CPU cpu;
    List<Task> queue;
    Task task, next_task;
    int time;
    
    /* Class constructor */
    public FCFS(List<Task> todo)
    {
        queue = todo;
    }

    /** 
     * Schedule keeps track of the tasks in the queue, 
     * For all of the tasks left in the queue, it calls 
     * "pickNextTask"  using the appropriate algorithm to find which task to run 
     * once ran, it then removes the task from the queue once it has finished.
     * It repeats the process until there are no more tasks in the queue
     * */
    public void schedule() 
    {
        while(!queue.isEmpty())
        {
            task = pickNextTask();
            time = task.getBurst();
            cpu.run(task, time);
            queue.remove(task);
        }
    }
    /**
     * pickNextTask contains the logic of the scheduling process
     * and how the next task is picked.
     * First Come First Served (FCFS): Pick task at the top of the queue 
     */
    public Task pickNextTask()
    {
        return queue.get(0);
    }
}