package hu.asd.towerdefence;

public class ConsoleDisplay implements TDActionListener{

	@Override
	public void onEnemyDamage(Enemy e,int damage) {
		Printer.print(e," lost " + damage + " HP ");
		if (e.getHP()<=0)
			Printer.print(e, " has died ");
	}

	@Override
	public void onTowerShooting(Tower t, Enemy e) {
		Printer.printShooting(t,e);
	}

	@Override
	public void onTowerFog(Tower t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnemyMovement(Enemy e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTowerNotShooting(Tower t) {
		Printer.print(t," not shooting ");
		
	}

	@Override
	public void onTowerUpgrade(Tower t) {
		// TODO Auto-generated method stub
		
	}

}
