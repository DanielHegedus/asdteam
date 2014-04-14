package hu.asd.towerdefence;
public class Gem {
	private int cost; //a varazsko ara
	
	//visszadja, hogy mennyibe kerul a gem
	public int getCost() {
		System.out.println("getCost()");
		return 5;
	}
	
	//beallitja a gem arat
	public void setCost(int i) {
		System.out.println("setCost(i)");
	}
}
