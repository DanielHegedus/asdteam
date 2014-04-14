package hu.asd.towerdefence;
import java.util.ArrayList;
import java.util.List;

public class Road extends Tile {
	private List<Enemy> enemies; // a Road-on tartozkodo ellensegek listaja
	
	//ellenseg belepeset kezeli
	public void enter(Enemy enemy) {
		System.out.println("enter("+ enemy.getClass().getName() +")");
		System.out.print("--> "+ enemy.getClass().getName() + ".");
		enemy.setRoad(this);
	}
	
	//ellenseg elhagyja a Road-ot
	public void leave(Enemy enemy) {
		System.out.println("leave("+ enemy.getClass().getName() +")");
	}
	
	//visszaadja a Road-on levo ellensegeket
	public List<Enemy> getEnemies() {
		System.out.println("getEnemies()");
		return enemies;
	}
	
	//van ellenseg a Road-on?
	public Enemy hasEnemy() {
		System.out.println("hasEnemy()");
		System.out.println("<-- Enemy:enemy");
		//ha van, visszaadjuk az elsot, egyebkent nullt.	
		return null; 
	}
	
	//beallitja az ellensegeket
	public void setEnemies(List<Enemy> enemies){
		System.out.println("setEnemies(enemies)");

	}
}
