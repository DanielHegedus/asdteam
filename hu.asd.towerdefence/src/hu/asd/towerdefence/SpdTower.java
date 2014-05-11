package hu.asd.towerdefence;
public class SpdTower extends Tower {
	//SpdGem-mel felfejlesztett torony osztalya
	public SpdTower(){
		super();
		speed=2; // TODO
		power=3;
		setTimeleft(0);
		defTimeleft=0;
		cost=5;
	}
}
