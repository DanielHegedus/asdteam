package hu.asd.towerdefence;

import hu.asd.towerdefence.view.GraphicDisplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) {
		MagicPower.setMP(1000);
		
		GraphicDisplay gd = new GraphicDisplay();
		Game game = new Game(gd);
		game.init();
		gd.setGame(game);
		gd.menu();

		//gd.setup();
		
		
//		game.getMap().addTower(2, 2);
//		game.getMap().addTower(6, 1);
//		game.getMap().addTower(8, 1);
//		game.getMap().addTower(8, 3);
//		game.getMap().addTower(8, 6);
//		game.getMap().addTower(8, 18);
//		game.getMap().setGem(new DmgGem());
//		game.getMap().upgradeTower(2, 2);
	//	game.start();


		Parser p = new Parser(game);
		while(true) {
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			try {
				p.parse(r.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
	}

}
