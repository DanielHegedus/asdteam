/**
 * TDActionListener.java
 * 
 * Interface for displaying basic game events
 */
package hu.asd.towerdefence;

public interface TDActionListener {
	
	public static final String NO_MP = "Not enough MP";
	public static final String WRONG_TILE = "Wrong tile selected";
	public static final String NO_GEM = "You need a gem";
	
	public void setGame(Game game);
	
	public void onEnemyAction(Enemy e);
	
	public void onMapAction(Tile t);
	
	public void onTowerAction(Tower t);
	
	public void onMPAction();
	
	public void onGemAction(Gem gem);
	
	public void onGameOver(boolean playerHasWon);
	
	public void onError(String message);
	
}
