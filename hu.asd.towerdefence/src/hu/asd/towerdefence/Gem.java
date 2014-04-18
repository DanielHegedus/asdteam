package hu.asd.towerdefence;
public class Gem {
	protected int cost; //a varazsko ara
	
	//visszadja, hogy mennyibe kerul a gem
	public int getCost() {
		return cost;
	}
	
	//beallitja a gem arat
	public void setCost(int i) {
		cost = i;
	}
}
