package hu.asd.towerdefence;

import hu.asd.towerdefence.view.GraphicDisplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) {
		GraphicDisplay gd = new GraphicDisplay();
		Game game = new Game(gd);
		gd.menu();
		gd.setGame(game);

		//Parser parser = new Parser(game);

		
		
	}

}
