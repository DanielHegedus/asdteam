package hu.asd.towerdefence;

public class Controller {

	private boolean newTower = false;
	private boolean newSwamp = false;
	private Game game;
	
	public void tileAction(Tile t) {
		if(newTower){
			getGame().getMap().addTower(t);
			newTower = false;
		}
		else if(newSwamp){
			getGame().getMap().addSwamp(t);
			newSwamp = false;
		}
		else if(game.getMap().getGem() != null){
			if(t instanceof Field)
				getGame().getMap().upgradeTower((Field)t);
			else if(t instanceof Swamp)
				getGame().getMap().upgradeSwamp((Swamp)t);
		}
		
		
	}
	
	public void setNewTower(boolean bool) {
		newSwamp = false;
		newTower = bool;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setNewSwamp(boolean b) {
		newTower = false;
		newSwamp = b;
	}

	public void buyGem(Gem gem) {
		game.buyGem(gem);
		
	}
}
