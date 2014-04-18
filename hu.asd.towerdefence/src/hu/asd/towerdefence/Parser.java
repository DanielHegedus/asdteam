/**
 * Parser.java
 * 
 * Provides means for controlling the game from console
 */

package hu.asd.towerdefence;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {

	private Game game;

	/** ctor, receives a reference to the game */
	public Parser(Game game) {
		this.game = game;
	}

	/**
	 * parses the given input string looking for the commands specified in the
	 * documentation
	 */
	public void parse(String in) {
		Scanner scanner = new Scanner(in);
		scanner.useDelimiter(" ");

		// if there is no input return
		if (!scanner.hasNext()) {
			scanner.close();
			return;
		}

		String cmd = scanner.next();

		switch (cmd) {

		// gives one or more ticks to the game
		case "tick":
			int n = 1;
			try {
				n = scanner.nextInt();
			} catch (Exception e) {

			} finally {
				for (int i = 0; i < n; i++) {
					game.getMap().onTick();
					Printer.print(game.getMap());
					System.out.println();
				}

			}
			break;

		// command for setting up the map
		case "initGame":
			game.init();
			break;

		// adds a tower to the field specified by x,y coordinates
		case "addTower":
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				String s = "";

				if (scanner.hasNext()) {
					s = scanner.next();
				}

				// check the optional arg
				if (s.equals("spd")) {
					game.getMap().addTower(x, y);
				} else if (s.equals("dmg")) {
					game.getMap().addTower(x, y);
				} else if (s.equals("-noMP")) {
					MagicPower.increase(DefTower.cost);
					game.getMap().addTower(x, y);

				} else
					game.getMap().addTower(x, y);

			} catch (NoSuchElementException e) {
				// print out usage info if the args are not right
				System.out.println("Usage: addTower x y [type | -noMP]");
			}
			break;

		case "addSwamp":
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				String s = null;

				if (scanner.hasNext()) {
					s = scanner.next();
				}

				// check the optional arg
				if (s.equals("super")) {
					game.getMap().addSwamp(x, y);
				} else if (s.equals("-noMP")) {
					MagicPower.increase(Swamp.cost);
					game.getMap().addSwamp(x, y);
				} else if (s == null)
					game.getMap().addSwamp(x, y);
				else
					Printer.printError("Usage: addTower x y [type | -noMP]");

			} catch (NoSuchElementException e) {
				// print out usage info if the args are not right
				Printer.printError("Usage: addTower x y [type | -noMP]");
			}
			break;

		// buys the given gem
		case "buyGem":
			String s;
			if (scanner.hasNext()) {
				s = scanner.next();
				Gem gem = null;
				if (s.equals("spd"))
					gem=new SpdGem();
				else if (s.equals("dmg"))
					gem=new DmgGem();
				else if (s.equals("swp"))
					gem=new SwpGem();
				else{
					//if type is neither dmg nor spd nor swp print error and return
					Printer.printError("Type can be spd, dmg or swp");
					scanner.close();
					return;
				}
				
				if (scanner.hasNext() && scanner.next().equals("-noMP")){
					MagicPower.increase(gem.getCost());
				}
				game.buyGem(gem);
				
			} else {
				Printer.printError("Usage: buyGem type [-noMP]. Type can be spd, dmg or swp");
			}
			break;

		// prints out all the enemies on the map
		case "printEnemies":
			Printer.printEnemies(game.getMap());
			break;

		// prints out the map
		case "printMap":
			Printer.print(game.getMap());
			break;

		// getting the value of the MP
		case "getMP":
			Printer.printMP();
			break;

		// setting the MP
		case "setMP":
			try {
				MagicPower.setMP(scanner.nextInt());
			} catch (NoSuchElementException e) {
				Printer.printError("Usage: setMP mp");
			}
			break;
			
		case "getGem":
			Printer.printGem(game);
			break;

		case "exit":
			scanner.close();
			System.exit(0);
			break;

		// if the input didn't match any command
		default:
			System.out.println("No such command: " + cmd);
		}

		scanner.close();
	}
}
