/**
 * Parser.java
 * 
 * Provides means for controlling the game from console
 */

package hu.asd.towerdefence;

import java.util.ArrayList;
import java.util.List;
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
					DefTower tmp = new DefTower();
					MagicPower.increase(tmp.getCost());
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

				// check the optional arg
				if (scanner.hasNext()) {
					String s = scanner.next();
					if (s.equals("super")) {
						game.getMap().addSwamp(x, y);
					} else if (s.equals("-noMP")) {
						MagicPower.increase(Swamp.cost);
						game.getMap().addSwamp(x, y);
					} else
						Printer.printError("Usage: addSwamp x y [type | -noMP]");
				} else {
					game.getMap().addSwamp(x, y);
				}

			} catch (NoSuchElementException e) {
				// print out usage info if the args are not right
				Printer.printError("Usage: addSwamp x y [type | -noMP]");
			}
			break;

		// buys the given gem
		case "buyGem":
			String s;
			if (scanner.hasNext()) {
				s = scanner.next();
				Gem gem = null;
				if (s.equals("spd"))
					gem = new SpdGem();
				else if (s.equals("dmg"))
					gem = new DmgGem();
				else if (s.equals("swp"))
					gem = new SwpGem();
				else {
					// if type is neither dmg nor spd nor swp print error and
					// return
					Printer.printError("Type can be spd, dmg or swp");
					scanner.close();
					return;
				}

				// if -noMP given add the cost of the gem to MP
				if (scanner.hasNext() && scanner.next().equals("-noMP")) {
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

		// displaying the current gem
		case "getGem":
			Printer.printGem(game);
			break;

		// shooting with a tower
		case "shoot":
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				// get the x,y tile
				int size = game.getMap().getSize();
				Tile t = game.getMap().getMap().get(x * size + y);
				Tower tower;

				// check if there is a tower on the tile
				if (t instanceof Field && ((Field) t).getTower() != null) {
					tower = ((Field) t).getTower();
				} else {
					// return if not
					Printer.printError("Selected tile doesn't have a tower");
					scanner.close();
					return;
				}

				// check for other args

				// initialize to avoid nullPointerException
				String arg1 = "", arg2 = "";

				if (scanner.hasNext()) {
					arg1 = scanner.next();
					if (scanner.hasNext())
						arg2 = scanner.next();
				}

				// check the args
				if (arg1.equals("-split") || arg2.equals("-split"))
					tower.setSplit(true);
				if (arg1.equals("-force") || arg2.equals("-force"))
					tower.setTimeleft(0);

				// shoot
				tower.onTick();

			} catch (NoSuchElementException e) {
				Printer.printError("Usage: shoot x y [-split] [-force]");
			}
			break;

		case "placeEnemy":
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				String type = scanner.next();
				Enemy e = null;

				switch (type) {
				case "hobbit":
					e = new Hobbit();
					break;
				case "elf":
					e = new Elf();
					break;
				case "human":
					e = new Human();
					break;
				case "dwarf":
					e = new Dwarf();
					break;
				default:
					Printer.printError("Possible values for type are: hobbit,human,elf,dwarf");
					scanner.close();
					return;
				}

				Tile t = getTile(x, y);

				if (t instanceof Road) {
					((Road) t).enter(e);
				} else
					Printer.printError("Enemies can only be placed on roads");

			} catch (Exception e) {
				Printer.printError("Usage: placeEnemy x y type");
			}
			break;

		case "setFog":
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				Tower t = getTowerOnField(x, y);

				if (t != null) {
					t.setFog(true);
				} else
					Printer.printError("There is no tower on the selected tile");

			} catch (NoSuchElementException e) {
				Printer.printError("Usage: setFog x y");
			}

			break;

		case "removeFog":
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				Tower t = getTowerOnField(x, y);

				if (t != null) {
					t.setFog(false);
				} else
					Printer.printError("There is no tower on the selected tile");

			} catch (NoSuchElementException e) {
				Printer.printError("Usage: setFog x y");
			}

			break;

		// damaging an enemy
		case "damageEnemy":
			String id = null;
			int damage = 0;

			// if there is an argument and it is not an integer it must be the
			// id
			if (scanner.hasNext() && !scanner.hasNextInt())
				id = scanner.next();

			// if there is an int it must be the damage
			if (scanner.hasNextInt()) {
				damage = scanner.nextInt();
			}

			// if no id was given damage all
			if (id == null) {
				if (damage != 0) {
					for (Enemy e : getEnemies()) {
						e.lowerHP(damage);
					}
				} else {
					// if no damage was given kill all
					for (Enemy e : getEnemies()) {
						e.lowerHP(e.getHP());
					}
				}
			} else {
				Enemy e = getEnemyById(id);
				if (e != null) {
					if (damage != 0)
						e.lowerHP(damage);
					else
						e.lowerHP(e.getHP());
				} else
					Printer.printError("No such enemy");

			}

			break;

		case "move":
			if (scanner.hasNext()) {
				Enemy e = getEnemyById(scanner.next());
				if (e != null) {
					if (scanner.hasNext()) {
						int r = scanner.nextInt();
						e.setChooseRoad(r);
					}
					e.move();
				} else
					Printer.printError("No such enemy");
			} else
				Printer.printError("Usage: move enemyID [direction]");

			break;

		case "enterMordor":
			if (scanner.hasNext()) {
				Enemy e = getEnemyById(scanner.next());
				if (e != null) {
					e.getRoad().leave(e);
					game.getMap().getMordor().enter(e);
				}
				Printer.printError("No such enemy");
			} else
				Printer.printError("Usage: enterMordor enemyID");

			break;

		case "upgradeTower":
			try {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				String st = "";

				if (scanner.hasNext()) {
					st = scanner.next();
				}

				if (st.equals("")) {
					game.getMap().upgradeTower(x, y);
				} else if (st.equals("spd")) {
					MagicPower.setMP(20);
					game.buyGem(new SpdGem());
					game.getMap().upgradeTower(x, y);
				} else if (st.equals("dmg")) {
					MagicPower.setMP(20);
					game.buyGem(new DmgGem());
					game.getMap().upgradeTower(x, y);
				}
			} catch (NoSuchElementException e) {
				// print out usage info if the args are not right
				System.out.println("Usage: upgradeTower x y [type]");
			}
			break;
			
		case "upgradeSwamp":
				try {
					int x = scanner.nextInt();
					int y = scanner.nextInt();

					String st = "";

					if (scanner.hasNext()) {
						st = scanner.next();
					}
					
					if(st.equals("-nogem")){
						MagicPower.setMP(20);
						game.buyGem(new SwpGem());
						game.getMap().upgradeSwamp(x,y);
					}
					else if(st.equals("")) {
						game.getMap().upgradeSwamp(x,y);
					}
					
			} catch (NoSuchElementException e) {
				// print out usage info if the args are not right
				Printer.printError("Usage: upgradeSwamp x y [-noMP]");
			}
			break;

		case "save":
			if (scanner.hasNext()){
				game.save(scanner.next());
			}else
				Printer.printError("Usage: save fileName");
			break;
			
		case "load":
			if (scanner.hasNext()){
				game.load(scanner.next());
			}else
				Printer.printError("Usage: save fileName");
			break;
			
		case "exit":
			scanner.close();
			System.exit(0);
			break;

		// if the input didn't match any command
		default:
			Printer.printError("No such command: " + cmd);
		}

		scanner.close();
	}

	private Tower getTowerOnField(int x, int y) {
		Tile t = getTile(x, y);
		if (t instanceof Field) {
			return ((Field) t).getTower();
		}
		return null;
	}

	private Tile getTile(int x, int y) {
		int size = game.getMap().getSize();
		return game.getMap().getMap().get(x * size + y);
	}

	private Enemy getEnemyById(String id) {
		for (Enemy e : getEnemies()) {
			if (e.getId().equals(id))
				return e;
		}

		return null;
	}

	private List<Enemy> getEnemies() {
		List<Enemy> enemies = new ArrayList<Enemy>();
		for (Tile t : game.getMap().getMap()) {
			if (t instanceof Road) {
				Road r = (Road) t;
				for (Enemy e : r.getEnemies()) {
					enemies.add(e);
				}
			}
		}
		return enemies;
	}

	private String getTowerId(Tower t) {
		int index = game.getMap().getMap().indexOf(t.getField());
		int size = game.getMap().getSize();
		String s = index / size + "," + index % size;
		return s;
	}
}
