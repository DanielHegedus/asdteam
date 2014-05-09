package hu.asd.towerdefence;

import java.io.Serializable;
import java.util.HashMap;

//ez az osztály tárolja a játék visszaállításához szükséges adatokat, ezt mentjük el
public class GameData implements Serializable{
	
	private HashMap<int[],Tower> towers;
	private HashMap<int[],Enemy> enemies;
	private HashMap<int[],Swamp> swamps;
	private Gem gem;
	private int mp;
	private int counter;
	
	public GameData(){
		setTowers(new HashMap<int[],Tower>());
		setEnemies(new HashMap<int[],Enemy>());
		setSwamps(new HashMap<int[],Swamp>());
	}
	
	public void addEnemy(int[] pos,Enemy e){
		getEnemies().put(pos, e);
	}
	
	public void addTower(int[] pos,Tower t){
		getTowers().put(pos, t);
	}
	
	public void addSwamp(int[] pos, Swamp s){
		getSwamps().put(pos, s);
	}
	
	public void setGem(Gem g){
		gem=g;
	}
	
	public Gem getGem(){
		return gem;
	}
	
	public void setMP(int mp){
		this.setMp(mp);
	}

	public HashMap<int[],Tower> getTowers() {
		return towers;
	}

	public void setTowers(HashMap<int[],Tower> towers) {
		this.towers = towers;
	}

	public HashMap<int[],Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(HashMap<int[],Enemy> enemies) {
		this.enemies = enemies;
	}

	public HashMap<int[],Swamp> getSwamps() {
		return swamps;
	}

	public void setSwamps(HashMap<int[],Swamp> swamps) {
		this.swamps = swamps;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}