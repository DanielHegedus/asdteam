/**
 * ConsoleDisplay.java
 * 
 * Basic console interface for displaying game events
 */

package hu.asd.towerdefence;

public class ConsoleDisplay{

//	private Game game;
//	
//	//set the game
//	public void setGame(Game game){
//		this.game=game;
//	}
//	
//	//print on enemy damage 
//	public void onEnemyDamage(Enemy e,int damage) {
//		Printer.print(e," lost " + damage + " HP ");
//		if (e.getHP()<=0)
//			Printer.print(e, " has died ");
//	}
//	
//	//print when tower is shooting
//	public void onTowerShooting(Tower t, Enemy e) {
//		Printer.printShooting(t,e);
//	}
//	
//	//print that the tower is in fog or not
//	@Override
//	public void onTowerFog(Tower t) {
//		Map m = game.getMap();
//		if (t.isInFog()){
//			Printer.print(t,"is in fog", m);
//		}else
//			Printer.print(t,"is no longer in fog", m);
//		
//	}
//	
//	@Override
//	public void onEnemyMovement(Enemy e) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	//print when tower not shooting
//	@Override
//	public void onTowerNotShooting(Tower t) {
//		Map m = game.getMap();
//		Printer.print(t," not shooting", m);
//		
//	}
//
//	@Override
//	public void onTowerUpgrade(Tower t) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	//print if enemy blocked
//	@Override
//	public void onEnemyBlock(Enemy e) {
//		Printer.print(e," stuck\n");
//		
//	}
//	
//	//print if swamp added
//	@Override
//	public void onSwampAdded(Swamp s) {
//		Printer.print(s,"created",game.getMap());
//		
//	}
//	
//	//print if you dont have enough MP
//	@Override
//	public void notEnoughMP() {
//		Printer.printError("Not enough MP");
//		
//	}
//	
//	//print if wrong tile selected
//	@Override
//	public void wrongTileSelected() {
//		System.out.println("Wrong tile selected");
//		
//	}
//
//	//print if player get MP
//	@Override
//	public void onMPGain() {
//		Printer.printMP();		
//	}
//	
//	//print if enemy created
//	@Override
//	public void onEnemyCreated(Enemy newEnemy) {
//		Printer.print(newEnemy," created\n");
//	}
//	
//	//print that no enemy in sight for the tower
//	@Override
//	public void onNoEnemyInSight(Tower tower) {
//		Map m = game.getMap();
//		Printer.print(tower," no enemy in sight", m);
//		
//	}
//	
//	//print that enemy entered into another road
//	@Override
//	public void onEnteredRoad(Enemy enemy, Road road) {
//		Printer.print(enemy," entered ");
//		Printer.printCoords(road, game.getMap());
//		System.out.println();
//	}
//	
//	//print that enemy left the road
//	@Override
//	public void onLeftRoad(Enemy enemy, Road road) {
//		Printer.print(enemy," left ");
//		Printer.printCoords(road, game.getMap());
//		System.out.println();
//		
//	}
//
//	@Override
//	public void onTowerAction(Tower t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onEnemyAction(Enemy e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onMapAction(Tile t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onMPAction() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onGemAction(Gem gem) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onGameOver(boolean playerHasWon) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onError(String message) {
//		// TODO Auto-generated method stub
//		
//	}

}
