/**
 * MapBuilder.java
 * 
 * Class for setting up the map, one tile has 4 neighbours
 * 
 * Changes:
 * -createMap takes a Map parameter
 */

package hu.asd.towerdefence;

public class MapBuilder {

	// palya elkeszitese
	public void createMap(Map map) {

		// 5x5 test map
		int mapX = 10;
		int mapY = 20;
		char[][] charmap = { 
				{'r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','f','m'},
				{'r','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','r','f','r'},
				{'r','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','r','f','r'},
				{'r','f','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','f','r'},
				{'r','f','r','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f'},
				{'r','r','r','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f'},
				{'r','f','r','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f'},
				{'r','f','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r'},
				{'r','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','f','r'},
				{'r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r'} };

		Tile[][] t = new Tile[mapX][mapY];

		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {

				if (charmap[i][j] == 'f')
					t[i][j] = new Field();
				else if (charmap[i][j] == 'r')
					t[i][j] = new Road();
				else if (charmap[i][j] == 'm')
					t[i][j] = new Mordor();

			}

		}

		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				if (i - 1 >= 0) {
					t[i - 1][j].setNeighbour(t[i][j]);
				}
				if (i + 1 < mapX) {
					t[i + 1][j].setNeighbour(t[i][j]);
				}
				if (j - 1 >= 0) {
					t[i][j - 1].setNeighbour(t[i][j]);
				}
				if (j + 1 < mapY) {
					t[i][j + 1].setNeighbour(t[i][j]);
				}
			}
		}

		for (Tile[] tl : t) {
			for (Tile tile : tl) {
				map.addTile(tile);
			}
		}
		
		map.setStart((Road) t[9][0]);
		map.setMordor((Mordor) t[0][19]);
		
		// add enemies
		//map.addEnemy(new Dwarf());
		map.addEnemy(new Hobbit());
		map.addEnemy(new Elf());
		map.setSize(mapY);

	}
}
