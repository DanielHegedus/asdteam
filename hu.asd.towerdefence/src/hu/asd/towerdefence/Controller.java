package hu.asd.towerdefence;
/**
 * Controller.java
 * 
 * Class for processing user input
 * 
 */
public class Controller {

	
	private boolean newTower = false;
	private boolean newSwamp = false;
	private Game game;
	
	/**
	 * Called when the user click on a tile
	 * @param t the tile clicked on
	 */
	public void tileAction(Tile t) {
		//if newTower is set, we add a new tower to the map
		if(newTower){
			getGame().getMap().addTower(t);
			newTower = false;
		}
		//if newSwamp is set, we add a new swamp
		else if(newSwamp){
			getGame().getMap().addSwamp(t);
			newSwamp = false;
		}
		
		//if neither is set and we have a gem, we upgrade based on the clicked tile
		else if(game.getMap().getGem() != null){
			if(t instanceof Field)
				getGame().getMap().upgradeTower((Field)t);
			else if(t instanceof Swamp)
				getGame().getMap().upgradeSwamp((Swamp)t);
		}
		
		
	}
	
	//tells the controller that the user wants to build a new tower
	public void setNewTower(boolean bool) {
		newSwamp = false;
		newTower = bool;
	}

	//setter and getter for the game
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	//tells the controller that the user wants to build a new swamp
	public void setNewSwamp(boolean b) {
		newTower = false;
		newSwamp = b;
	}

	//tells the controller that the user wants to buy a gem
	public void buyGem(Gem gem) {
		game.buyGem(gem);
		
	}
}
