package hu.asd.towerdefence;
public class DmgTower extends Tower {
	//DmgGem-mel megerositett torony objektuma
	public DmgTower(){
		super();
		speed=2;
		power=5;
		setTimeleft(2);
		defTimeleft=2;
		cost=5;
	}
}
