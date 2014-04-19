/**
 * ConsoleDisplay.java
 * 
 * Basic console interface for displaying game events
 */

package hu.asd.towerdefence;

public class ConsoleDisplay implements TDActionListener{

	private Game game;
	
	public void setGame(Game game){
		this.game=game;
	}

	@Override
	public void onEnemyDamage(Enemy e,int damage) {
		Printer.print(e," lost " + damage + " HP ");
		if (e.getHP()<=0)
			Printer.print(e, " has died ");
	}

	@Override
	public void onTowerShooting(Tower t, Enemy e) {
		Printer.printShooting(t,e);
	}

	@Override
	public void onTowerFog(Tower t) {
		if (t.isInFog()){
			Printer.print(t,"is in fog");
		}else
			Printer.print(t,"is no longer in fog");
		
	}

	@Override
	public void onEnemyMovement(Enemy e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTowerNotShooting(Tower t) {
		Printer.print(t," not shooting ");
		
	}

	@Override
	public void onTowerUpgrade(Tower t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnemyBlock(Enemy e) {
		Printer.print(e," blocked");
		
	}

	@Override
	public void onSwampAdded(Swamp s) {
		Printer.print(s,"created",game.getMap());
		
	}

	@Override
	public void notEnoughMP() {
		Printer.printError("Not enough MP");
		
	}

	@Override
	public void wrongTileSelected() {
		System.out.println("Wrong tile selected");
		
	}

	@Override
	public void onMPGain() {
		Printer.printMP();		
	}

	@Override
	public void onEnemyCreated(Enemy newEnemy) {
		Printer.print(newEnemy," created");
	}

	@Override
	public void onNoEnemyInSight(Tower tower) {
		Printer.print(tower," no enemy in sight");
		
	}

	@Override
	public void onEnteredRoad(Enemy enemy, Road road) {
		Printer.print(enemy," entered ");
		Printer.printCoords(road, game.getMap());
		System.out.println();
	}

	@Override
	public void onLeftRoad(Enemy enemy, Road road) {
		Printer.print(enemy," left ");
		Printer.printCoords(road, game.getMap());
		System.out.println();
		
	}

}
