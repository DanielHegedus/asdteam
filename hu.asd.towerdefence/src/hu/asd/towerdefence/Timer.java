package hu.asd.towerdefence;
public class Timer {
	private Game game; // jatekunk peldanya
	
	public Timer(Game game){
		this.game=game;
	}
	
	//elinditja a timert
	public void start() {
		System.out.println("start()");
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.getMap().onTick();
		}
		
	}
}
