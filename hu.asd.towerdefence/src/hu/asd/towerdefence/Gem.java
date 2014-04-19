package hu.asd.towerdefence;

import java.io.Serializable;

public class Gem implements Serializable{
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
