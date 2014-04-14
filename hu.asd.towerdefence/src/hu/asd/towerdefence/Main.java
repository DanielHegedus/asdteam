package hu.asd.towerdefence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	//TODO
	 static Game game = new Game();
	 static Swamp swamp = new Swamp();
	 static GameData gd = new GameData();
	 static Gem gem = new Gem();
	 static  MapBuilder mapb = new MapBuilder();
	 static  MagicPower mp = new MagicPower();
	 static  Map map = new Map();
	 static  Mordor mordor = new Mordor();
	 static  Field field = new Field();
	 static  DefTower tower = new DefTower();
	 static  Timer timer = new Timer();
	 static  Road road = new Road();
	 static  Road road1 = new Road();
	 static  Road road2 = new Road();
	 static  Hobbit hobbit = new Hobbit();
	 static  Human human = new Human();
	
	//TODO 
	public static void main (String[] args){
		
		starter();
	}
	
	//allapotgep a szkeleton kiprobalasara TODO
	public static void starter(){
		int i;
		System.out.println("");
		System.out.println("FÕMENÜ");
		System.out.println("1. Inicializálás");
		System.out.println("2. Torony lerakás");
		System.out.println("3. Tick (ellenség léptetése, torony lövés)");
		System.out.println("4. Torony sebzi az ellenséget");
		System.out.println("5. Ellenség meghal");
		System.out.println("6. Varázskõ vásárlása");
		System.out.println("7. Torony fejlesztése");
		System.out.println("8. Játékos veszít");
		System.out.println("9. Játékos gyõzelem");
		System.out.println("10. Újraindítás");
		System.out.println("11. Mentés");
		System.out.println("12. Betöltés");
		System.out.println("13. Mocsár lerakása");
		System.out.println("14. Mocsár fejlesztése");

		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			i = Integer.parseInt(br.readLine());
			switch(i){
			case 1: System.out.print("--> game.");
					game.init();
					
					System.out.println("<--");
					starter();
					break;
					 
			case 2:	System.out.print("--> map.");
					map.addTower(field);
					
					System.out.println("<--");
					starter();
					break;
			
			case 3: System.out.print("--> map.");
					map.onTick();
					
					System.out.println("<--");
					starter();
					break;
			
			case 4: 
					// LOOP
					System.out.print("--> road1.");
					road.hasEnemy();
					
					String s = "n";
					
					System.out.println(" [?] Van ellenség a torony hatósugarában? y/n");
					try{
						s = br.readLine();
					}
					catch(IOException e){
						e.printStackTrace();
						}
					
					if (s.equals("y")){
					System.out.print("--> hobbit.");
					hobbit.shoot(tower);
					}
					
					// LOOP VEGE
					
					// LOOP
					
					System.out.print("--> road2.");
					road.hasEnemy();
					
					s = "n";
					
					System.out.println(" [?] Van ellenség a torony hatósugarában? y/n");
					try{
						s = br.readLine();
					}
					catch(IOException e){
						e.printStackTrace();
						}
					
					if (s.equals("y")){
					System.out.print("--> hobbit.");
					hobbit.shoot(tower);
					}
					
					// LOOP VEGE
					
					System.out.println("<--");
					starter();
					break;
					
			case 5: System.out.print("--> hobbit.");
					hobbit.getHP();
					
					s = "y";
					
					System.out.println(" [?] Maradt még HP-ja az ellenségnek? y/n");
					try{
						s = br.readLine();
					}
					catch(IOException e){
						e.printStackTrace();
						}
					if (s.equals("n")){
					
					System.out.print("--> map.");
					map.killEnemy(hobbit);
					
					System.out.print("--> mp.");
					mp.increase(hobbit);
					}
					
					// LOOP
					
					System.out.print("--> human.");
					human.getHP();
					
					s = "y";
					
					System.out.println(" [?] Maradt még HP-ja az ellenségnek? y/n");
					try{
						s = br.readLine();
					}
					catch(IOException e){
						e.printStackTrace();
						}
					
					if (s.equals("n")){
					
					System.out.print("--> map.");
					map.killEnemy(human);
					
					System.out.print("--> mp.");
					mp.increase(human);
					}
					
					System.out.println("<--");
					starter();
					break;
					
			case 6: System.out.print("--> game.");
					game.buyGem(gem);
					System.out.println("<--");
					
					starter();
					break;
					
			case 7: System.out.print("--> map.");
					map.upgradeTower(field);
					
					System.out.println("<--");
					starter();
					break;
					
			case 8: // LOOP
					System.out.print("--> mordor.");
					mordor.hasEnemy();
					
					s = "n";
					for (int j=0; !s.equals("y"); j++){
					
					
					System.out.println(" [?] Beért az ellenség Mordorba? y/n");
					try{
						s = br.readLine();
					}
					catch(IOException e){
						e.printStackTrace();
						}
					
					if (s.equals("y")){
							System.out.print("--> game.");
							game.gameOver(true);
							}
					}
					
					System.out.println("<--");
					starter();
					break;
					
			case 9: // LOOP
					System.out.print("--> map.");
					map.checkEnemies();
					
					s = "y";
					
					for (int j=0; !s.equals("n"); j++){
					
					System.out.println(" [?] Maradtak még ellenségek? y/n");
					try{
						s = br.readLine();
					}
					catch(IOException e){
						e.printStackTrace();
						}
					
					if (s.equals("n")){
						System.out.print("--> game.");
						game.gameOver(true);
						}
					}
					
					// LOOP VEGE
					
					System.out.println("<--");
					starter();
					break;
					
			case 10: System.out.print("--> game.");
					game.restart();
					
					System.out.println("<--");
					starter();
					break;
					
			case 11: System.out.print("--> game.");
					game.save();
					
					System.out.println("<--");
					starter();
					break;
					
			case 12: System.out.print("--> game.");
					game.load();
					
					System.out.print("--> game.");
					game.init();
					
					System.out.println("<--");
					starter();
					break;
					
			case 13: System.out.print("--> map.");
					map.addSwamp(road);
					
					System.out.println("<--");
					starter();
					break;
					
			case 14: System.out.print("--> map.");
					map.upgradeSwamp(swamp);
					
					// OPT
					
					s = "n";
					
					System.out.println(" [?] Van Varázskövünk mocsár fejlesztésre? y/n");
					try{
						s = br.readLine();
					}
					catch(IOException e){
						e.printStackTrace();
						}
					
					if (s.equals("y")){
					
					System.out.println(" --> create ss:SuperSwamp");
					System.out.print(" --> swamp.");
					swamp.getNeighbours();
					System.out.println(" <-- l:List");
					
					SuperSwamp ss = new SuperSwamp();
					
					System.out.print(" --> ss.");
					ss.setNeighbour(road1);
					
					System.out.print(" --> ss.");
					ss.setNeighbour(road2);
					
					System.out.print(" --> mp.");
					mp.decrease(ss);
					}
					
					System.out.println("<--");
					starter();
					break;
					
			
		
			default: starter();
					break; 
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
