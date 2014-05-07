/**
 * Game.java
 * 
 * Changes:
 * 
 * 
 * */

package hu.asd.towerdefence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Game {

	private Map map;
	private MapBuilder mapBuilder;
	private Timer timer;
	private TDActionListener listener;

	public Game() {
		mapBuilder = new MapBuilder();
		timer = new Timer(this);
	}

	public Game(TDActionListener listener) {
		this();
		this.listener = listener;
		listener.setGame(this);

	}

	// jatekter inicializalasa, megcsinalja a map-et
	public void init() {
		setMap(new Map(this));
		map.addListener(listener);
		mapBuilder.createMap(getMap());
	}

	// jatek inditasa
	public void start() {
		//System.out.println("--> timer.");
		timer.start();
	}

	// a parameterben megadott varazsko vasarlasa
	public void buyGem(Gem gem) {
		if (MagicPower.decrease(gem)) {
			map.setGem(gem);
		} else
			listener.notEnoughMP();

	}

	// jatek vege TODO
	public void gameOver(boolean playerWon) {
		Printer.printGameOver(playerWon);
	}

	// jatek ujrainditasa
	public void restart() {
		System.out.println("restart()");
		System.out.print(" --> game.");
		init();
	}

	// jatek mentese TODO
	public void save(String fileName) {
		try {
			OutputStream file = new FileOutputStream(fileName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(map.getData());
			
			output.close();
			buffer.close();
			file.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// betolti az adatokat a jatekba
	public void load(String fileName) {
		try {
			InputStream file = new FileInputStream(fileName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			
			GameData gd = (GameData) input.readObject();
			
			input.close();
			buffer.close();
			file.close();
			
			init();
			map.setData(gd);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
