/**
 * DiningServer.java
 *
 * This class contains the methods called by the philosophers. Implements the methods
 * from the DiningServer interface.
 * 
 * 
 * Written by:
 * Sara Hamidi Student ID: 028780685
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl implements DiningServer
{  

	enum State {THINKING, HUNGRY, EATING};
	State[] state = new State[5];
	Condition[] self = new Condition[5];
	Lock lock = new ReentrantLock();
	
	// Constructor
	public DiningServerImpl() {
	
		//all the philosopher start off in the "thinking" state
	    //create a new lock for
		for (int i = 0; i < 5; i++) {
			self[i] = lock.newCondition();
			
			state[i] = State.THINKING;
			System.out.println("Philosopher " + i + " is thinking");
		}
		
		//starts the threads
		for (int i = 0; i < 5; i++) {
			Philosopher phi = new Philosopher(i, this);
			Thread phlsr = new Thread(phi);
			phlsr.start();
		}
		
		
	}

	//Only one philosopher can take forks/eat at a time when the lock is locked
	@Override
	public void takeForks(int i) {
		lock.lock();
		
		state[i] = State.HUNGRY;
		System.out.println("Philosopher " + i + " is hungry");
		// See if we can eat now
		test(i);
		// If not, wait
		if (state[i] != State.EATING) {
			try {
				self[i].await();
			} catch (InterruptedException e) {
				System.out.println("Error: Interrupted Exception with philosopher " + i + " waiting for forks");
			}
		}
		
		lock.unlock();
	}

	//called when a philosopher is done eating
	@Override
	public void returnForks(int i) {
		lock.lock();
		
		state[i] = State.THINKING;
		System.out.println("Philosopher " + i + " is done eating");
		System.out.println("Philosopher " + i + " is thinking");
		// determines if left and right neighbors need the forks (signals for the people
		//in the waiting in takeForks() method
		test((i + 4) % 5);
		test((i + 1) % 5);
		
		lock.unlock();
	}
	
	// Tests to see if the philosopher at the given index can eat, and sets
	//the state to eating if so
	private void test(int i) {
		if ( (state[(i + 4) % 5] != State.EATING) && 
				(state[i] == State.HUNGRY) &&
				(state[(i + 1) % 5] != State.EATING) )  {
			state[i] = State.EATING;
			System.out.println("Philosopher " + i + " is eating");
			self[i].signal(); // Let the other philosophers know something's changed
		}
	}
}