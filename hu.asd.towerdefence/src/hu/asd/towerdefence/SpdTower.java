package hu.asd.towerdefence;
public class SpdTower extends Tower {
	//SpdGem-mel felfejlesztett torony osztalya
	public SpdTower(){
		super();
		speed=2; // TODO
		power=1;
		setTimeleft(2);
		defTimeleft=2;
		cost=5;
	}
}
