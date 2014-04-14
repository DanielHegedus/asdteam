package hu.asd.towerdefence;
public class MapBuilder {
	
	//palya elkeszitese
	public void createMap() {
		System.out.println(" --> mapb.createMap()");
		
		Map map = new Map();
		System.out.println("  --> create map:Map");
		
		Road road1 = new Road();
		System.out.println("  --> create road1:Road");
		
		Road road2 = new Road();
		System.out.println("  --> create road2:Road");
		
		Field field1 = new Field();
		System.out.println("  --> create field1:Field");
		
		Field field2 = new Field();
		System.out.println("  --> create field2:Field");
		
		MagicPower mp = new MagicPower();
		System.out.println("  --> create mp:MagicPower");
		
		// LOOP -- szomszedok beallitasa
		
		System.out.print("  --> road1.");
		road1.setNeighbour(road2);
		
		System.out.print("  --> map.");
		map.addTile(road1);
		
		System.out.print("  --> road2.");
		road2.setNeighbour(road1);
		
		System.out.print("  --> map.");
		map.addTile(road2);
		
		// LOOP VEGE
		
		// LOOP -- szomszedok beallitasa TODO
		
		System.out.print("  --> field1.");
		field1.setNeighbour(field2);
	
		System.out.print("  --> map.");
		map.addTile(field1);
		
		System.out.print("  --> field2.");
		field2.setNeighbour(field1);
		
		System.out.print("  --> field2.");
		field2.setNeighbour(road1);
		
		System.out.print("  --> road1.");
		road1.setNeighbour(field2);
	
		System.out.print("  --> map.");
		map.addTile(field2);
		
		// LOOP VEGE
		
		// LOOP -- ellensegek krealasa és hozzaadasa
		
		Hobbit hobbit = new Hobbit();
		System.out.println("  --> create hobbit:Hobbit");
		System.out.print("  --> map.");
		map.addEnemy(hobbit);
		
		Human human = new Human();
		System.out.println("  --> create human:Human");
		System.out.print("  --> map.");
		map.addEnemy(human);
		
		// LOOP VEGE
		
		System.out.println(" <--");
		

	}
}
