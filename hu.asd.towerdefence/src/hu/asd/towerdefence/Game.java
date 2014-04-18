/**
 * Game.java
 * 
 * Changes:
 * 
 * 
 * */

package hu.asd.towerdefence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private Map map;
	private MapBuilder mapBuilder;
	private Timer timer;
	private TDActionListener listener;

	public Game(){
		setMap(new Map(this));
		mapBuilder=new MapBuilder();
		timer = new Timer();
	}
	
	public Game(TDActionListener listener){
		this();
		this.listener=listener;
		map.addListener(listener);
	}
	
	//jatekter inicializalasa, megcsinalja a map-et
	public void init() {
		mapBuilder.createMap(getMap());
	}
	
	
	//jatek inditasa 
	public void start() {
		System.out.println("--> timer.");
		timer.start();
	}
	
	//a parameterben megadott varazsko vasarlasa
	public void buyGem(Gem gem) {
		if (MagicPower.decrease(gem)){
			map.setGem(gem);
		}else listener.notEnoughMP();
			
	}
	
	//jatek vege TODO
	public void gameOver(boolean playerWon) {
		Printer.printGameOver(playerWon);
	}
	
	//jatek ujrainditasa
	public void restart() {
		System.out.println("restart()");
		System.out.print(" --> game.");
		init();
	}
	
	//jatek mentese TODO
	public void save() {
		
	}
	
	//betolti az adatokat a jatekba
	public void load() {
		

	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
