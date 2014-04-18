/**
 * TDActionListener.java
 * 
 * Interface for displaying basic game events
 */
package hu.asd.towerdefence;

public interface TDActionListener {
	
	/*Enemy*/

	/**called when an enemy suffers damage */
	public void onEnemyDamage(Enemy e,int damage);
	
	/** enemy moves a tile */
	public void onEnemyMovement(Enemy e);
	
	/**enemy is blocked by a swamp */
	public void onEnemyBlock(Enemy e);
	
	/*Tower*/
	
	/**tower shooting*/
	public void onTowerShooting(Tower t, Enemy e);
	
	/** event when a tower is not shooting on a tick */
	public void onTowerNotShooting(Tower t);
	
	/** event when a tower is upgraded*/
	public void onTowerUpgrade(Tower t);
	
	/** tower fog */
	public void onTowerFog(Tower t);
	
	/* Swamp */
	
	/** a new swamp is added to the map */
	public void onSwampAdded(Swamp s);
	
	/* Other */
	
	/** player doesn't have enough MP to purchase a tower or swamp */
	public void notEnoughMP();
	
	/** field is selected for swamp or road for tower */
	public void wrongTileSelected();
	
	/**called when the player gains magicpower */
	public void onMPGain();
	
}
