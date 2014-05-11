package hu.asd.towerdefence;
/**
 * Timer.java
 * 
 * Timer class that provides the ticks for the game
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer{
	
	//Delay between ticks
	private static final int TIMER_DELAY = 1000;
	//game reference
	private Game game;
	//swing timer
	private javax.swing.Timer timer;
	
	/**
	 * Creates the timer
	 * @param g the game which receives the ticks
	 */
	public Timer(Game g){
		this.game=g;
		timer = new javax.swing.Timer(TIMER_DELAY, new ActionListener() {
		     public void actionPerformed(ActionEvent e) {
		    	 //when a timer action is performed (TIME_DELAY has passed) send a tick to the map
		        game.getMap().onTick();
		     }
		  });
	}
	
	//start the timer
	public void start() {
		
		timer.start();
		
	}
	
	//stop the timer
	public void stop(){
		timer.stop();
	}

}
