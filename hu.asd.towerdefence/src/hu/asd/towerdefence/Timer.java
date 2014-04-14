package hu.asd.towerdefence;
public class Timer {
	private Game game; // jatekunk peldanya
	
	//elinditja a timert
	public void start() {
		System.out.println("start()");
		game.getMap().onTick();
	}
}
