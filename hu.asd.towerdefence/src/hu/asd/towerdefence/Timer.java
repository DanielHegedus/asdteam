package hu.asd.towerdefence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer{
	private static final int TIMER_DELAY = 1000;
	private Game game; // jatekunk peldanya
	private javax.swing.Timer timer;
	
	public Timer(Game game){
		this.game=game;
	}
	
	//elinditja a timert
	public void start() {
		timer = new javax.swing.Timer(TIMER_DELAY, new ActionListener() {
		     public void actionPerformed(ActionEvent e) {
		        game.getMap().onTick();
		     }
		  });
		timer.start();
		
	}
	
	public void stop(){
		timer.stop();
	}

}
