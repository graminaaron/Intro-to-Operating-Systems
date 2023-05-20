/**
 * Non-preemptive priority scheduling algorithm using RR.
 *
 * This algorithm will run tasks according to round-robin scheduling.
 * 
 * Written by:
 * Aaron Garcia Student ID: 030556771
 */
 
import java.util.*;

// Your code here
public class RR implements Algorithm 
{
    
    CPU cpu;
    List<Task> queue;
    Task task, next_task;
    int RUNFOR = 10;
    int time;
    int iter = 0;
    
    /* Class constructor */
    public RR(List<Task> todo)
    {
        queue = todo;
    }

    /** 
     * Schedule keeps track of the tasks in the queue, 
     * For all of the tasks left in the queue, it calls 
     * "pickNextTask"  using the appropriate algorithm to find which task to run 
     * It runs the task for a duration of the time quantum   
     * "RUNFOR" or task time left, whichever smaller, 
     * before moving to the next task
     * After running, it subtracts the time left from the task  
     * It repeats the process until there are no more tasks in the queue
     * */     
    public void schedule() 
    {
        while(!queue.isEmpty())
        {
            task = pickNextTask();
            time = task.getBurst();
            cpu.run(task, Math.min(time, RUNFOR));
            task.setBurst(time - RUNFOR);
        }
    }
    /**
     * pickNextTask contains the logic of the scheduling process
     * and how the next task is picked.
     * Round Robin: Return a task. 
     * Determine if the remaining runtime that has been kept track by 
     * the scheduler is smaller than the time quantum "RUNFOR." 
     * If so, it removes the task, marking it finished. 
     * It increments to the following task using iter, or remains
     * on current task if it is last in the queue
     */
    public Task pickNextTask()
    {
        next_task = queue.get(iter);
        if (next_task.getBurst() <= RUNFOR)
        {
            queue.remove(next_task);
        }
        
        iter = iter + 1;
        if (iter >= queue.size())
        {
            iter = 0;
        }
        return next_task;
    }
}