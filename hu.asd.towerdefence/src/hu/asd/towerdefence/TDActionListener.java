/**
 * Interface for displaying enemy and tower actions
 */
package hu.asd.towerdefence;

/**
 * @author dani
 *
 */
public interface TDActionListener {

	/**called when an enemy suffers damage */
	public void onEnemyDamage(Enemy e,int damage);
	
	/** enemy moves a tile */
	public void onEnemyMovement(Enemy e);
	
	/**tower shooting*/
	public void onTowerShooting(Tower t, Enemy e);
	
	/** event when a tower is not shooting on a tick */
	public void onTowerNotShooting(Tower t);
	
	/** event when a tower is upgraded*/
	public void onTowerUpgrade(Tower t);
	
	/** tower fog */
	public void onTowerFog(Tower t);
}
