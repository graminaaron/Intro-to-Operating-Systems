/**
 * Non-preemptive priority scheduling algorithm.
 * 
 * Written by:
 * Aaron Garcia Student ID: 030556771
 */
 
import java.util.*;

// Your code here
public class Priority implements Algorithm 
{
    List<Task> queue;
    Task task, next_task;
    CPU cpu;
    int time, highest_priority;
    
    /* Class constructor */
    public Priority(List<Task> todo)
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
     * Priority: Compare all tasks left in the queue, 
     * select the one with the highest priority amongst its peers
     */
    public Task pickNextTask()
    {
        highest_priority = 0;
        for (Task cur_task : queue)
        {
            int task_priority = cur_task.getPriority();
            if (task_priority > highest_priority)
            {
                highest_priority = task_priority;
                next_task = cur_task;
            }
        }
        return next_task;
    }
}