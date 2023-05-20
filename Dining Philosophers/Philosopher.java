/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 *
 * Written by:
 * Sara Hamidi Student ID: 028780685
 */


public class Philosopher implements Runnable
{
 

    private int num; // The philosopher's number
    private DiningServerImpl diningp;
    private long createTime;

    //defines what a philosopher is/its attributes
    public Philosopher(int number, DiningServerImpl diningServerImpl) {
        num = number;
        diningp = diningServerImpl;
        createTime = System.currentTimeMillis(); //time the philosopher is created
    }

    //each philosopher eats for 5 millis
    private void eat() {
        double ms = 0;
        while (ms <= 5000) {
            ms = System.currentTimeMillis() - createTime;
        }
    }

    //method used by the philosopher to eat and return forks when done eating
    @Override
    public void run() {
        while (true) {
        	diningp.takeForks(num);
            eat();
            diningp.returnForks(num);
        }
    }
}