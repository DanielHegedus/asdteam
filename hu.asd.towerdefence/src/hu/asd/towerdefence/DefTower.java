package hu.asd.towerdefence;
@SuppressWarnings("serial")
public class DefTower extends Tower {
	//basic tower's class
	public DefTower(){
		//tower attributes
		super();
		speed=2;
		power=3;
		setTimeleft(2);
		defTimeleft=2;
		cost=5;
	}
	
}
