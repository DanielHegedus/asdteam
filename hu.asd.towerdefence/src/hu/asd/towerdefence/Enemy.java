package hu.asd.towerdefence;
public abstract class Enemy {
	private int hp; //ellensegunk elete
	private int blockTime;	// akkor mehet tovabb az ellenseg, ha ez nulla, ha mocsarba lep, akkor lesz nem nulla, igy lassul a mozgasa
	private Road road=new Road();
	private Road previousRoad=new Road();
	
	//lovi az ellensegunket a parameterben megadott tower, meghivja a doDamage-et 
	public void shoot(Tower tower) {
		System.out.println("shoot("+ tower.getClass().getName() +")");
		System.out.print("<-- tower.");
		tower.doDamage(this);
	}
	
	//visszaadja az ellenseg hp-jat
	public int getHP() {
		System.out.println("getHP");
		System.out.println("<-- hp:int ");
		return 10;
	}
	
	//beallitja az ellenseg hp-jat
	public void setHP(int i) {
		System.out.println("setHP");
	}
	
	//csokkenti az ellenseg hp-jat a parameterben megadott int i ertekkel
	public void lowerHP(int i) {
		System.out.println("lowerHP(int i)");
		

	}
	
	//minden hivasnal eggyel csokkenti a blocktime-ot, visszaadja annak értékét.
	public int timeToMove() {
		System.out.println("timeToMove");
		return 0;
	}
	
	//beallitja a blocktime valtozo erteket
	public void setBlockTime() {
		System.out.println("setBlockTimeP");
	}
	
	//beallitja, hogy melyik uton van az ellenseg, illetve a previousRoadot is
	public void setRoad(Road road){
		System.out.println("setRoad");
	}
	
	
	//visszaadja melyik uton van az ellenseg
	public Road getRoad(){
		System.out.println("getRoad");
		return road;
	}
	
	//mozgatja az ellenséget
	public void move(){
		System.out.println("move");
		//lekérdezi szomszédokat
		System.out.print("--> road.");
		road.getNeighbours();
		//ezekbõl egyet kiválasztva tovább lép arra
		Road nextRoad=new Road();
		System.out.print("--> road.");
		road.leave(this);
		System.out.print("--> nextRoad.");
		nextRoad.enter(this);
		
	}
}
