package hu.asd.towerdefence;
public class DefTower extends Tower {

	protected static int cost=5;
	
	public DefTower(){
		super();
		speed=2;
		power=1;
		setTimeleft(2);
		defTimeleft=2;
		cost=5;
	}
	
}
