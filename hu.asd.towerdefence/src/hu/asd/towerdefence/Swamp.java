package hu.asd.towerdefence;
public class Swamp extends Road {
	
	private int power;	//mocsarunk lassito ereje
	private int cost;	//mocsarunk ara
	
	//blockolja(lassitja) az ellensegunk tovabbhaladasat bizonyos ideig
	public void block(Enemy enemy) {
		System.out.println("block(" + enemy.getClass().getName() + ")");
	}
	
	//visszaadja a mocsar arat
	public int getCost() {
		System.out.println("getCost()");
		return cost;
	}
}
