/**
 * TDActionListener.java
 * 
 * Interface for displaying basic game events
 */
package hu.asd.towerdefence;

public interface TDActionListener {
	
	//Default error messages
	public static final String NO_MP = "Not enough MP";
	public static final String WRONG_TILE = "Wrong tile selected";
	public static final String NO_GEM = "You need a gem";
	
	//setting the game reference
	public void setGame(Game game);
	
	//something happened with an enemy
	public void onEnemyAction(Enemy e);
	
	//something happened on the map
	public void onMapAction(Tile t);
	
	//something happened with a tower
	public void onTowerAction(Tower t);
	
	//MP is changed
	public void onMPAction();
	
	//the gem has changed
	public void onGemAction(Gem gem);
	
	//the game is over
	public void onGameOver(boolean playerHasWon);
	
	//an error has occured
	public void onError(String message);
	
}
