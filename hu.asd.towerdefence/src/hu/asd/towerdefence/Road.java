/**
 * Road.java
 * 
 * Class for the roads
 * 
 * Changes
 * -setEnemies removed
 * -enemies is copyOnWriteArraylist
 */

package hu.asd.towerdefence;
import java.util.ArrayList;
import java.util.List;

public class Road extends Tile {
	protected List<Enemy> enemies; // a Roadon tartozkodo ellensegek listaja
	
	public Road(){
		enemies=new ArrayList<Enemy>();
	}
	
	//ellenseg belepeset kezeli
	public void enter(Enemy enemy) {
		if (enemy.getActionListener()==null){
			enemy.setActionListener(listener);
		}
		enemies.add(enemy);
		enemy.setRoad(this);
	}
	
	//ellenseg elhagyja a Road-ot
	public void leave(Enemy enemy) {
		enemies.remove(enemy);
	}
	
	//visszaadja a Road-on levo ellensegeket
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	//van ellenseg a Road-on?
	public Enemy hasEnemy() {
		if (enemies.size()>0)
			return enemies.get(0);
		else
			return null;
		
	}
	
}
